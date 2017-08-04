package live.itrip.api.controller;

import com.alibaba.fastjson.JSONObject;
import live.itrip.api.common.Config;
import live.itrip.api.common.Constants;
import live.itrip.api.service.intefaces.IAppHomeService;
import live.itrip.api.service.intefaces.IAppVisibilityService;
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
public class AppPlanController extends BaseController {


    /**
     * 获取行程具体信息
     *
     * @param response
     */
    @RequestMapping(value = "/plan/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    void getMessageDetail(@PathVariable Long id, HttpServletResponse response) {
//        this.writeResponse(response, iUserMessageService.selectMessageDetail(id));
    }
}
