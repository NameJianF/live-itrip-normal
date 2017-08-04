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
public class AppMessageController extends BaseController {

    @Autowired
    private IUserMessageService iUserMessageService;

    /**
     * 消息列表
     *
     * @param response
     * @param uid
     * @param page
     * @param pageSize
     * @param lastMsgId
     */
    @RequestMapping(value = "/msg/list", method = RequestMethod.GET)
    public
    @ResponseBody
    void selectMessageList(HttpServletResponse response
            , @RequestParam(value = "type", required = false) String msgType
            , @RequestParam(value = "uid", required = false) Long uid
            , @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "pageSize", required = false) Integer pageSize
            , @RequestParam(value = "lastMsgId", required = false) Long lastMsgId) {


        this.writeResponse(response, iUserMessageService.selectMessageList(msgType, uid, page, pageSize, lastMsgId));
    }

    /**
     * 获取消息具体信息
     *
     * @param response
     */
    @RequestMapping(value = "/msg/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    void getMessageDetail(@PathVariable Long id, HttpServletResponse response) {
        this.writeResponse(response, iUserMessageService.selectMessageDetail(id));
    }

    /**
     * 对话消息
     *
     * @param response
     */
    @RequestMapping(value = "msg/dia", method = RequestMethod.GET)
    public
    @ResponseBody
    void getDialogMessages(
            @RequestParam(value = "fromId", required = false) Long fromId
            , @RequestParam(value = "toId", required = false) Long toId
            , @RequestParam(value = "lastMsgId", required = false) Long lastMsgId
            , HttpServletResponse response) {
        this.writeResponse(response, iUserMessageService.selectDialogMessages(fromId, toId, lastMsgId));
    }


}
