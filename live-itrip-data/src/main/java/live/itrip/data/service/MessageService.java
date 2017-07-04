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
     * @param msgType
     * @param userId
     * @param page
     * @param pageSize
     * @param lastMsgId
     * @return
     */
    public static ArrayList<MessageModel> selectMessages(String msgType, Long userId, int page, int pageSize, Long lastMsgId) {
        return MessageDao.selectMessages(msgType, userId, page, pageSize, lastMsgId);
    }

    /**
     * 查询消息具体信息
     *
     * @param msgId
     * @return
     */
    public static MessageModel selectMessageDetail(Long msgId) {
        return MessageDao.selectMessageDetail(msgId);
    }

    /**
     * 添加消息到数据库
     *
     * @param model
     * @return
     */
    public static Integer insert(MessageModel model) {
        return MessageDao.insert(model);
    }

    /**
     * 标记为已读
     *
     * @param msgId
     * @return
     */
    public static Integer updateMsgReadme(Long msgId) {
        return MessageDao.updateMsgReadme(msgId);
    }

}

