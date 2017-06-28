package live.itrip.common.controller;

import live.itrip.common.request.RequestHeader;

/**
 * Created by Feng on 2017/6/28.
 */
public interface IController {
    /**
     * 根据 apikey 验证客户端
     *
     * @return
     */
    boolean validateClientApiKey(String clientapikey);

    /**
     * 校验用户 sig
     *
     * @param secretkey
     * @param header
     * @param jsonString
     * @return
     */
    boolean validateSig(String secretkey, RequestHeader header, String jsonString);

    /**
     * 校验时间戳
     *
     * @param timestamp
     * @return
     */
    boolean validateTimestamp(Long timestamp);


    /**
     * 校验用户token
     *
     * @param usertoken
     * @return
     */
    boolean validateToken(String usertoken);
}
