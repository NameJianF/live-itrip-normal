package live.itrip.api.service.impls;

import live.itrip.api.service.BaseService;
import live.itrip.api.service.intefaces.IUserMessageService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
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
    public void selectMessageList(HttpServletResponse response, Long userId, int page, int pageSize, Long lastMsgId) {
        BaseResult result = new BaseResult();

        ArrayList<MessageModel> list = MessageService.selectMessages(userId, page, pageSize, lastMsgId);
        if (list != null) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(list);
            this.writeResponse(response, result);
            return;
        }
        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);

    }
}
