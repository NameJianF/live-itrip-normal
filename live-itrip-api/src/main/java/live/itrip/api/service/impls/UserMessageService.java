package live.itrip.api.service.impls;

import live.itrip.api.service.BaseService;
import live.itrip.api.service.intefaces.IUserMessageService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import live.itrip.data.common.Constants;
import live.itrip.data.model.web.MessageModel;
import live.itrip.data.service.MessageService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Feng on 2017/6/28.
 */
@Service
public class UserMessageService extends BaseService implements IUserMessageService {


    @Override
    public BaseResult selectMessageList(String msgType, Long userId, int page, int pageSize, Long lastMsgId) {
        BaseResult result = new BaseResult();

        ArrayList<MessageModel> list = MessageService.selectMessages(msgType, userId, page, pageSize, lastMsgId);
        if (list != null) {
            // 查询用户名称和头像

            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(list);
            return result;
        }
        result.setError(ErrorCode.UNKNOWN);
        return result;
    }

    @Override
    public MessageModel selectMessageDetail(Long msgId) {
        MessageModel model = MessageService.selectMessageDetail(msgId);
//        BaseResult result = new BaseResult();
//        result.setData(model);

        // 查询用户名称和头像


        if (model != null && Constants.MessageInfo.README_NO_READ.equals(model.getReadme())) {
            // set message readed
            MessageService.updateMsgReadme(msgId);
        }

        return model;
    }

    @Override
    public BaseResult selectDialogMessages(Long fromId, Long toId, Long lastMsgId) {
        BaseResult result = new BaseResult();

        ArrayList<MessageModel> list = MessageService.selectDialogMessages(fromId, toId, lastMsgId);
        if (list != null && list.size() > 0) {
            // 查询用户名称和头像

            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(list);
            return result;
        }
        result.setError(ErrorCode.UNKNOWN);
        return result;
    }
}
