package live.itrip.api.service.intefaces;

import live.itrip.common.response.BaseResult;
import live.itrip.data.model.web.MessageModel;

/**
 * Created by Feng on 2017/6/28.
 */
public interface IUserMessageService {

    BaseResult selectMessageList(String msgType, Long userId, int page, int pageSize, Long lastMsgId);

    MessageModel selectMessageDetail(Long msgId);

    BaseResult selectDialogMessages(Long fromId, Long toId, Long lastMsgId);
}
