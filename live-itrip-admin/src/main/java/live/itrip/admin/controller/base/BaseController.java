package live.itrip.admin.controller.base;


import live.itrip.admin.model.ClientApiKey;
import live.itrip.admin.service.intefaces.IClientApiKeyService;
import live.itrip.common.controller.IController;
import live.itrip.common.request.RequestHeader;
import live.itrip.common.response.IResponseWrite;
import live.itrip.common.response.ResponseHelper;
import live.itrip.common.util.SigUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2016/7/5.
 * <p>
 * Controller 基类
 */
public class BaseController implements IController, IResponseWrite {
    private static final Long TIMEOUT = 1000L * 60;

    @Autowired
    private IClientApiKeyService iClientApiKeyService;


    public ClientApiKey getClientApiKey(String clientapikey) {
        return iClientApiKeyService.selectClientKey(clientapikey);
    }

    @Override
    public boolean validateClientApiKey(String clientapikey) {
        if (StringUtils.isEmpty(clientapikey)) {
            return false;
        }
        ClientApiKey client = iClientApiKeyService.selectClientKey(clientapikey);
        return (client != null && StringUtils.isNotEmpty(client.getSecretKey()));
    }

    /**
     * 校验用户 sig
     *
     * @param baseClientKey
     * @param header
     */
    public boolean validateSig(ClientApiKey baseClientKey, RequestHeader header, String jsonString) {
        String secretkey = baseClientKey.getSecretKey();
        return validateSig(secretkey, header, jsonString);
    }

    @Override
    public boolean validateSig(String secretkey, RequestHeader header, String jsonString) {
        // 计算sig
        String sig = SigUtils.getSig(jsonString, secretkey);
        if (!sig.equals(header.getSig())) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateTimestamp(Long timestamp) {
        if (timestamp == null) {
            return false;
        }
        if (System.currentTimeMillis() - timestamp > TIMEOUT) {
            // 时间超时
            return false;
        }
        return true;
    }

    @Override
    public boolean validateToken(String usertoken) {
        return true;
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
