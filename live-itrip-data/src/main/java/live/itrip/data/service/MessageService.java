package live.itrip.data.service;

import live.itrip.data.dao.web.MessageDao;
import live.itrip.data.model.web.MessageModel;

import java.util.ArrayList;

/**
 * Created by Feng on 2017/6/30.
 */
public class MessageService {

    /**
     * 查询消息列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @param lastMsgId
     * @return
     */
    public static ArrayList<MessageModel> selectMessages(Long userId, int page, int pageSize, Long lastMsgId) {
        return MessageDao.selectMessages(userId, pageSize, lastMsgId);
    }

    public static Integer insert(MessageModel model) {
        return MessageDao.insert(model);
    }

    public static Integer update(MessageModel model) {
        return MessageDao.update(model);
    }
}

