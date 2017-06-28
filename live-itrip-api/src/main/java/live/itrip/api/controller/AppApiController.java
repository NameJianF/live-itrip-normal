package live.itrip.api.controller;

import com.alibaba.fastjson.JSONObject;
import live.itrip.api.common.Config;
import live.itrip.api.common.Constants;
import live.itrip.api.service.intefaces.IAppApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2017/6/26.
 * <p>
 * Restful openapi
 */
@RestController
public class AppApiController extends BaseController {

    @Autowired
    private IAppApiService iAppApiService;


    /**
     * @param response
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public
    @ResponseBody
    void hello(HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", "Hello.");
        this.writeResponse(response, jsonObject);
    }

    /**
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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public
    @ResponseBody
    void selectItemList(HttpServletResponse response
            , @RequestParam(value = "q", required = false, defaultValue = "") Integer key
            , @RequestParam(value = "sort", required = false, defaultValue = "") String sort
            , @RequestParam(value = "order", required = false, defaultValue = "") String order
            , @RequestParam(value = "page", required = false, defaultValue = "") String page
            , @RequestParam(value = "pageSize", required = false, defaultValue = "") Integer pageSize) {

//        iAppApiService.selectItemList(response, key, sort, order, page, pageSize);
    }


}
