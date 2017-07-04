package live.itrip.sso.interfaces;

import live.itrip.common.response.BaseResult;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2016/7/7.
 */
public interface IWriteResponse {

    /**
     * 参数无效
     *
     * @param paramName
     */
    BaseResult paramInvalid(String paramName);

    /**
     * 参数无效
     *
     * @param paramName
     */
    BaseResult paramInvalid(String paramName, String paramValue);
}
