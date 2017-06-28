package live.itrip.common.response;

import com.alibaba.fastjson.JSON;
import live.itrip.common.Encoding;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Feng on 2017/6/28.
 */
public class ResponseHelper {

    public static void writeResponse(HttpServletResponse response, Object obj) {
        try {
            String json = JSON.toJSONString(obj);
            Logger.debug("Write Response Json:" + json);
            response.setCharacterEncoding(Encoding.UTF8);
            PrintWriter out;
            out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } catch (IOException e) {
            Logger.error(e);
        }
    }

    public static void paramInvalid(HttpServletResponse response, String paramName) {
        BaseResult result = new BaseResult();
        result.setCode(ErrorCode.PARAM_INVALID.getCode());
        result.setMsg(String.format(ErrorCode.PARAM_INVALID.getMessage() + "(%s).", paramName));

        ResponseHelper.writeResponse(response, result);
    }
}
