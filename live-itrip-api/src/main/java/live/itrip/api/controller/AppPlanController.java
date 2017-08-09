package live.itrip.api.controller;

import live.itrip.api.service.intefaces.IAppPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2017/6/26.
 * <p>
 * Restful openapi
 */
@RestController
public class AppPlanController extends BaseController {

    @Autowired
    private IAppPlanService iAppPlanService;

    /**
     * 获取行程具体信息
     *
     * @param response
     */
    @RequestMapping(value = "/plan/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    void getMessageDetail(@PathVariable Long id, HttpServletResponse response) {
        this.writeResponse(response, iAppPlanService.selectPlanDetail(id));
    }

    /**
     * 获取行程具体信息
     *
     * @param response
     */
    @RequestMapping(value = "/plan/recommends/{category}", method = RequestMethod.GET)
    public
    @ResponseBody
    void getRecommendList(@PathVariable String category, HttpServletResponse response) {
        this.writeResponse(response, iAppPlanService.selectRecommendList(category));
    }
}
