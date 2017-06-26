package live.itrip.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.controller.base.AbstractController;
import live.itrip.admin.service.intefaces.IAppApiService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2017/6/26.
 * <p/>
 * Restful openapi
 */
@Controller
public class AppApiController extends AbstractController {

    @Autowired
    private IAppApiService iAppApiService;

    @RequestMapping(value = "/api/list", method = RequestMethod.GET)
    public
    @ResponseBody
    void selectItemList(HttpServletResponse response
            , @RequestParam(value = "q", required = false, defaultValue = "") Integer key
            , @RequestParam(value = "sort", required = false, defaultValue = "") String sort
            , @RequestParam(value = "order", required = false, defaultValue = "") String order
            , @RequestParam(value = "page", required = false, defaultValue = "") String page
            , @RequestParam(value = "pageSize", required = false, defaultValue = "") Integer pageSize) {

        iAppApiService.selectItemList(response, key, sort, order, page, pageSize);
    }
}
