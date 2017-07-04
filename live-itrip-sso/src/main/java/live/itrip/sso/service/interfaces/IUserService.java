package live.itrip.sso.service.interfaces;


import live.itrip.common.response.BaseResult;
import live.itrip.sso.common.exception.ApiException;

/**
 * Created by Feng on 2016/2/14.
 */
public interface IUserService {
    void test();

    BaseResult register(String decodeJson) throws ApiException;

    BaseResult updatePassword(String decodeJson) throws ApiException;

    BaseResult updateUserInfo(String decodeJson) throws ApiException;

    BaseResult retrievePassword(String decodeJson) throws ApiException;


    BaseResult selectUserNameAvatar(String decodeJson) throws ApiException;
}
