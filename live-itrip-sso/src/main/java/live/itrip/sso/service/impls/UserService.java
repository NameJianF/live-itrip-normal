package live.itrip.sso.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import live.itrip.common.security.Md5Utils;
import live.itrip.common.util.UuidUtils;
import live.itrip.common.validator.PasswordParam;
import live.itrip.sso.bean.*;
import live.itrip.sso.common.Constants;
import live.itrip.sso.common.exception.ApiException;
import live.itrip.sso.dao.UserMapper;
import live.itrip.sso.model.User;
import live.itrip.sso.service.BaseService;
import live.itrip.sso.service.interfaces.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by JianF on 2015/12/22.
 */
@Service
public class UserService extends BaseService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void test() {
        User user = userMapper.selectByUserName("admin");
        System.err.println(user.toString());
    }


    /**
     * 注册
     *
     * @param decodeJson
     */
    @Override
    public BaseResult register(String decodeJson) throws ApiException {
        try {
            UserRegisterRequest userRegisterRequest = JSON.parseObject(decodeJson, UserRegisterRequest.class);
            BaseResult userRegisterResponse = new BaseResult();
            userRegisterResponse.setOp(userRegisterRequest.getOp());

            User user = userRegisterRequest.getData();
            if (user != null) {
                String salt = UuidUtils.getUuidLowerCase(false);
                String pwd = PasswordParam.getPassword(user.getPassword(), salt, user.getCiphertext());
                user.setPassword(pwd);
                user.setSalt(salt);
                user.setCreateTime(System.currentTimeMillis());

                int userid = userMapper.insertSelective(userRegisterRequest.getData());
                if (userid > 0) {
                    userRegisterResponse.setCode(ErrorCode.SUCCESS.getCode());

                    JSONObject data = new JSONObject();
                    data.put("uid", userid);
                    userRegisterResponse.setData(data);
                } else {
                    userRegisterResponse.setCode(ErrorCode.UNKNOWN.getCode());
                }
            }

            return userRegisterResponse;
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }

    /**
     * 修改密码
     *
     * @param decodeJson
     */
    @Override
    public BaseResult updatePassword(String decodeJson) throws ApiException {
        try {
            UserUpdatePwdRequest userUpdatePwdRequest = JSON.parseObject(decodeJson, UserUpdatePwdRequest.class);
            BaseResult userUpdatePwdResponse = new BaseResult();
            userUpdatePwdResponse.setOp(userUpdatePwdRequest.getOp());

            if (userUpdatePwdRequest.getUid() == null) {
                return this.paramInvalid("uid", userUpdatePwdRequest.getUid().toString());
            }

            if (StringUtils.isEmpty(userUpdatePwdRequest.getOriginPwd())) {
                return this.paramInvalid("originPwd", userUpdatePwdRequest.getOriginPwd());
            }

            if (StringUtils.isEmpty(userUpdatePwdRequest.getPassword())) {
                return this.paramInvalid("password", userUpdatePwdRequest.getPassword());
            }

            User user = this.userMapper.selectByPrimaryKey(userUpdatePwdRequest.getUid());
            if (user == null) {
                userUpdatePwdResponse.setCode(ErrorCode.USER_AUTH_INVALID.getCode());
                userUpdatePwdResponse.setMsg(String.format("参数uid(%s)无效,无此用户。", userUpdatePwdRequest.getUid()));
                return userUpdatePwdResponse;
            }

            // 正常用户,验证密码
            String password = PasswordParam.getPassword(userUpdatePwdRequest.getOriginPwd()
                    , user.getSalt(), userUpdatePwdRequest.getCiphertext());
            if (StringUtils.isNotEmpty(password) && password.equals(user.getPassword())) {
                // 原密码正确
                String pwd = PasswordParam.getPassword(userUpdatePwdRequest.getPassword(), user.getSalt(), PasswordParam.PASSWORD_CIPHERTEXT);
                int ret = this.userMapper.updatePasswordById(user.getId(), pwd);
                if (ret > 0) {
                    // success
                    userUpdatePwdResponse.setCode(ErrorCode.SUCCESS.getCode());
                } else {
                    // failed
                    userUpdatePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
                }
            } else {
                // 原密码错误
                userUpdatePwdResponse.setCode(ErrorCode.USERNAME_PWD_INVALID.getCode());
                userUpdatePwdResponse.setMsg(ErrorCode.USERNAME_PWD_INVALID.getMessage());
            }
            return userUpdatePwdResponse;
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }

    /**
     * 修改用户信息
     *
     * @param decodeJson
     */
    @Override
    public BaseResult updateUserInfo(String decodeJson) throws ApiException {
        try {
            UserUpdateRequest userUpdateRequest = JSON.parseObject(decodeJson, UserUpdateRequest.class);
            BaseResult userUpdateResponse = new BaseResult();
            userUpdateResponse.setOp(userUpdateRequest.getOp());

            User user = userUpdateRequest.getData();
            if (user == null) {
                userUpdateResponse.setCode(ErrorCode.PARAM_INVALID.getCode());
                userUpdateResponse.setMsg("参数user(null)无效。");
                return userUpdateResponse;
            }
            if (userUpdateRequest.getData().getId() == null) {
                userUpdateResponse.setCode(ErrorCode.PARAM_INVALID.getCode());
                userUpdateResponse.setMsg(String.format("参数user id(%s)无效。", userUpdateRequest.getData().getId()));
                return userUpdateResponse;
            }

            user.setPassword(null); // 不修改密码
            user.setSalt(null);// 不修改salt

            int ret = this.userMapper.updateByPrimaryKeySelective(user);
            if (ret > 0) {
                // success
                userUpdateResponse.setCode(ErrorCode.SUCCESS.getCode());
            } else {
                // failed
                userUpdateResponse.setCode(ErrorCode.UNKNOWN.getCode());
            }

            return userUpdateResponse;
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }

    /**
     * 找回密码
     */
    @Override
    public BaseResult retrievePassword(String decodeJson) throws ApiException {
        try {
            RetrievePwdRequest retrievePwdRequest = JSON.parseObject(decodeJson, RetrievePwdRequest.class);
            BaseResult retrievePwdResponse = new BaseResult();
            retrievePwdResponse.setOp(retrievePwdRequest.getOp());

            // 发送邮件
            if (!StringUtils.isEmpty(retrievePwdRequest.getEmail())) {
                User user = this.userMapper.selectByUserName(retrievePwdRequest.getEmail());
                String initpwd = UuidUtils.getUuidLowerCase(false).substring(0, 6);

                String pwd = PasswordParam.getPassword(initpwd, user.getSalt(), PasswordParam.PASSWORD_NOT_CIPHERTEXT);

                int ret = this.userMapper.updatePasswordById(user.getId(), pwd);
                if (ret > 0) {
                    // success
                    retrievePwdResponse.setCode(ErrorCode.SUCCESS.getCode());
                    // TODO send email

                } else {
                    // failed
                    retrievePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
                }
                return retrievePwdResponse;
            }

            // 发送短信
            if (!StringUtils.isEmpty(retrievePwdRequest.getMobile())) {
                User user = this.userMapper.selectByUserName(retrievePwdRequest.getEmail());
                String initpwd = UuidUtils.getUuidLowerCase(false).substring(0, 6);
                String pwd = PasswordParam.getPassword(initpwd, user.getSalt(), PasswordParam.PASSWORD_NOT_CIPHERTEXT);

                int ret = this.userMapper.updatePasswordById(user.getId(), pwd);
                if (ret > 0) {
                    // success
                    retrievePwdResponse.setCode(ErrorCode.SUCCESS.getCode());
                    // TODO send sms

                } else {
                    // failed
                    retrievePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
                }
                return retrievePwdResponse;
            }

            retrievePwdResponse.setCode(ErrorCode.UNKNOWN.getCode());
            return retrievePwdResponse;
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }

    @Override
    public BaseResult selectUserNameAvatar(String decodeJson) throws ApiException {
        try {
            UserNameAvatarRequest userNameAvatarRequest = JSON.parseObject(decodeJson, UserNameAvatarRequest.class);

            BaseResult baseResult = new BaseResult();
            baseResult.setOp(userNameAvatarRequest.getOp());

            // 查询数据
            if (userNameAvatarRequest.getIds() != null && userNameAvatarRequest.getIds().size() > 0) {
                ArrayList<User> userList = this.userMapper.selectUserNameAvatar(userNameAvatarRequest.getIds());

                if (userList != null) {
                    // success
                    JSONArray array = new JSONArray();
                    for (User user : userList) {
                        JSONObject object = new JSONObject();
                        object.put("id", user.getId());
                        object.put("userName", user.getUserName());
                        object.put("img", user.getImg());
                        array.add(object);
                    }

                    baseResult.setCode(ErrorCode.SUCCESS.getCode());
                    baseResult.setData(array);

                } else {
                    // failed
                    baseResult.setCode(ErrorCode.UNKNOWN.getCode());
                }

                return baseResult;
            }

            baseResult.setCode(ErrorCode.UNKNOWN.getCode());
            return baseResult;
        } catch (Exception ex) {
            throw new ApiException(ex.getMessage(), ex, true);
        }
    }
}
