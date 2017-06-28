package live.itrip.api.controller;


import live.itrip.common.controller.IController;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.response.IResponseWrite;
import live.itrip.common.response.ResponseHelper;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/7/5.
 * <p>
 * Controller 基类
 */
public class BaseController implements IController, IResponseWrite {

    @Override
    public boolean validateClientApiKey(String clientapikey) {
        return false;
    }

    @Override
    public boolean validateSig(String secretkey, RequestHeader header, String jsonString) {
        return false;
    }

    @Override
    public boolean validateTimestamp(Long timestamp) {
        return false;
    }

    @Override
    public boolean validateToken(String usertoken) {
        return false;
    }

    @Override
    public void writeResponse(HttpServletResponse response, Object obj) {
        ResponseHelper.writeResponse(response, obj);
    }

    @Override
    public void paramInvalid(HttpServletResponse response, String paramName) {
        ResponseHelper.paramInvalid(response, paramName);
    }

}
