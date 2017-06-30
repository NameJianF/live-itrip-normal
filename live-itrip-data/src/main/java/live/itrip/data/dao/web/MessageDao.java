package live.itrip.data.dao.web;

import live.itrip.data.db.DbHelper;
import live.itrip.data.model.web.MessageModel;
import live.itrip.data.util.Logger;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Feng on 2017/6/30.
 */
public class MessageDao {

    /**
     * 查询消息列表
     *
     * @param userId
     * @param pageSize
     * @param lastMsgId
     * @return
     */
    public static ArrayList<MessageModel> selectMessages(Long userId, int pageSize, Long lastMsgId) {
        ArrayList<MessageModel> msgList = new ArrayList<>();
        if (userId == null || userId <= 0) {
            return msgList;
        }

        if (pageSize <= 0) {
            pageSize = 20;
        }

        try {
            StringBuffer stringBuffer = new StringBuffer("select id,type,user_from,user_to,content,create_time from " + MessageModel.TABLE_NAME);
            List<Object> params = new ArrayList<Object>();
            stringBuffer.append(" where user_to = ?");
            params.add(userId);
            if (lastMsgId != null && lastMsgId > 0) {
                stringBuffer.append(" and id < ?");
                params.add(lastMsgId);
            }
            stringBuffer.append(" order by id desc limit ?;");
            params.add(pageSize);

            List list = DbHelper.query(stringBuffer.toString(), new MapListHandler(), params.toArray());
            if (list != null && list.size() > 0) {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    Map<String, Object> item = (Map<String, Object>) iterator.next();
                    MessageModel model = MessageModel.getBean(item);
                    msgList.add(model);
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage(), e);
        }
        return msgList;
    }

    public static Integer insert(MessageModel model) {
        if (StringUtils.isEmpty(model.getType())) {
            Logger.error("message type is null");
            return 0;
        }

        if (model.getUserFrom() == null) {
            Logger.error("message from is null");
            return 0;
        }
        if (model.getUserTo() == null) {
            Logger.error("message to is null");
            return 0;
        }

        List<Object> params = new ArrayList<Object>();
        StringBuffer sqlBuffer = new StringBuffer("insert into " + MessageModel.TABLE_NAME);
        sqlBuffer.append(" (type,user_from,user_to,content,create_time)");
        sqlBuffer.append(" values(?,?,?,?,?)");

        params.add(model.getType());
        params.add(model.getUserFrom());
        params.add(model.getUserTo());
        params.add(model.getContent());
        params.add(model.getCreateTime());

        try {
            Long ret = DbHelper.insert(sqlBuffer.toString(), params.toArray());
            return ret.intValue();
        } catch (SQLException e) {
            Logger.error(e.getMessage(), e);
        }
        return -1;
    }

    public static Integer update(MessageModel model) {
//        if (StringUtils.isEmpty(cardInfo.getCardNo())) {
//            Logger.error("card NO is null");
//            return 0;
//        }
//        if (StringUtils.isEmpty(cardInfo.getCardType())) {
//            Logger.error("card type is null");
//            return 0;
//        }
//        if (StringUtils.isEmpty(cardInfo.getAdviser())) {
//            Logger.error("adviser is null");
//            return 0;
//        }
//
//        if (cardInfo.getPrice() == null) {
//            Logger.error("card price is null");
//            return 0;
//        }
//        List<Object> params = new ArrayList<Object>();
//        StringBuffer sqlBuffer = new StringBuffer("update " + TableNames.EDU_CARD_INFO + " set ");
//        sqlBuffer.append(" card_no = ?, ");
//        params.add(cardInfo.getCardNo());
//        sqlBuffer.append(" card_type = ?, ");
//        params.add(cardInfo.getCardType());
//        if (cardInfo.getTotalTimes() != null) {
//            sqlBuffer.append(" total_times = ?, ");
//            params.add(cardInfo.getTotalTimes());
//        }
//        if (cardInfo.getUsedTimes() != null) {
//            sqlBuffer.append(" used_times = ?, ");
//            params.add(cardInfo.getUsedTimes());
//        }
//        sqlBuffer.append(" price = ?, ");
//        params.add(cardInfo.getPrice());
//
//        if (cardInfo.getDiscount() != null) {
//            sqlBuffer.append(" discount = ?, ");
//            params.add(cardInfo.getDiscount());
//        }
//        sqlBuffer.append(" adviser = ?, ");
//        params.add(cardInfo.getAdviser());
//        if (cardInfo.getFlag() != null) {
//            sqlBuffer.append(" flag = ?, ");
//            params.add(cardInfo.getFlag());
//        }
//        sqlBuffer.append(" update_time = ? ");
//        params.add(System.currentTimeMillis());
//
//        sqlBuffer.append(" where id = ? ");
//        params.add(cardInfo.getId());
//        try {
//            return DbHelper.update(sqlBuffer.toString(), params.toArray());
//        } catch (SQLException e) {
//            Logger.error(e.getMessage(), e);
//        }
        return -1;
    }
}
