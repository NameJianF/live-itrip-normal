package live.itrip.common.response;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2016/7/7.
 */
public interface IResponseWrite {
    /**
     * Write Response
     *
     * @param response
     * @param obj
     */
    void writeResponse(HttpServletResponse response, Object obj);

    /**
     * 参数无效
     *
     * @param response
     * @param paramName
     */
    void paramInvalid(HttpServletResponse response, String paramName);
}
