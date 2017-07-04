package live.itrip.sso.service.interfaces;


import live.itrip.common.response.BaseResult;
import live.itrip.sso.common.exception.ApiException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Feng on 2016/3/8.
 */
public interface ISsoService {

    /**
     * login
     *
     * @param decodeJson
     */
    BaseResult login(String decodeJson, HttpServletRequest request) throws ApiException;

    /**
     * logout
     *
     * @param decodeJson
     */
    BaseResult logout(String decodeJson, HttpServletRequest request) throws ApiException;

    /**
     * 用户鉴权
     *
     * @param decodeJson
     */
    BaseResult authUser(String decodeJson) throws ApiException;
}
