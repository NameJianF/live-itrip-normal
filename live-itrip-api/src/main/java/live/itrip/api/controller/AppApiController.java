package live.itrip.api.controller;

import com.alibaba.fastjson.JSONObject;
import live.itrip.api.common.Config;
import live.itrip.api.common.Constants;
import live.itrip.api.service.intefaces.IUserMessageService;
import live.itrip.data.model.web.MessageModel;
import live.itrip.data.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by 建锋 on 2017/6/26.
 * <p>
 * Restful openapi
 */
@RestController
public class AppApiController extends BaseController {

    @Autowired
    private IUserMessageService iUserMessageService;


    /**
     * @param response
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public
    @ResponseBody
    void hello(HttpServletResponse response) {

//        for (int i = 0; i < 50; i++) {
//            MessageModel model = new MessageModel();
//            model.setType("1");
//            model.setUserFrom(10L + i);
//            model.setUserTo(1L);
//            model.setContent("test message ================ " + i + " ============");
//            model.setCreateTime(System.currentTimeMillis() + i * 100000);
//
//            Integer ret = MessageService.insert(model);
//            System.err.println("ret : " + ret);
//        }

        ArrayList<MessageModel> list = MessageService.selectMessages(1L, 1, 30, 0L);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", list);
        this.writeResponse(response, jsonObject);
    }

    /**
     * 获取app配置的最新版本信息
     *
     * @param response
     */
    @RequestMapping(value = "/ver/{type}", method = RequestMethod.GET)
    public
    @ResponseBody
    void getAppVersion(@PathVariable String type, HttpServletResponse response) {
        if (Constants.AppType.APP_ANDROID.equalsIgnoreCase(type)) {
            // android

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("versionCode", Config.AndroidAppVersion.VERSION_CODE);
            jsonObject.put("versionName", Config.AndroidAppVersion.VERSION_NAME);
            jsonObject.put("desc", Config.AndroidAppVersion.UPDATE_DESC);
            jsonObject.put("downloadUrl", Config.AndroidAppVersion.DOWNLOAD_URL);
            jsonObject.put("publishDate", Config.AndroidAppVersion.PUBLISH_DATE);
            this.writeResponse(response, jsonObject);

        } else {
            this.paramInvalid(response, "AppType");
        }
    }

    @RequestMapping(value = "/list/msg", method = RequestMethod.GET)
    public
    @ResponseBody
    void selectMessageList(HttpServletResponse response
            , @RequestParam(value = "uid", required = false) Long uid
            , @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "pageSize", required = false) Integer pageSize
            , @RequestParam(value = "lastMsgId", required = false) Long lastMsgId) {

        iUserMessageService.selectMessageList(response, uid, page, pageSize, lastMsgId);
    }


}
