package live.itrip.data.dao.web;

import live.itrip.data.common.Constants;
import live.itrip.data.db.DbHelper;
import live.itrip.data.model.web.MessageModel;
import live.itrip.data.util.Logger;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static ArrayList<MessageModel> selectMessages(String msgType, Long userId, int page, int pageSize, Long lastMsgId) {
        if (userId == null || userId <= 0) {
            return null;
        }

        if (pageSize <= 0) {
            pageSize = Constants.MessageInfo.PAGE_SIZE;
        }

        if (page < 1) {
            page = 1;
        }

        try {
            // sql 使用 as 别名，是为了处理ResultSetHandler数据映射
            StringBuffer stringBuffer = new StringBuffer("select id,type,user_from as userFrom,user_to as userTo,content,readme,");
            stringBuffer.append(" create_time as createTime,update_time as updateTime from " + MessageModel.TABLE_NAME);
            List<Object> params = new ArrayList<Object>();
            stringBuffer.append(" where user_to = ?");
            params.add(userId);

            if (Constants.MessageInfo.MESSAGE_TYPE_SYSTEM.equals(msgType)) {
                // 系统消息
                stringBuffer.append(" and type = ?");
                params.add(Constants.MessageInfo.MESSAGE_TYPE_SYSTEM);
            } else if (Constants.MessageInfo.MESSAGE_TYPE_USER.equals(msgType)) {
                // 用户消息
                stringBuffer.append(" and type = ?");
                params.add(Constants.MessageInfo.MESSAGE_TYPE_USER);
            } else {
                // 全部消息
            }

            if (page == 1) {
                // 向下刷新，查找最新，lastMsgId 为第一条记录id
                if (lastMsgId != null && lastMsgId > 0) {
                    stringBuffer.append(" and id > ?");
                    params.add(lastMsgId);
                }
            } else {
                // 向上刷新，查找之前数据，lastMsgId 为最后一条记录id
                if (lastMsgId != null && lastMsgId > 0) {
                    stringBuffer.append(" and id < ?");
                    params.add(lastMsgId);
                }
            }

            stringBuffer.append(" order by id desc limit ?;");
            params.add(pageSize);
            ResultSetHandler<List<MessageModel>> handler = new BeanListHandler<>(MessageModel.class);

            List<MessageModel> msgList = DbHelper.query(stringBuffer.toString(), handler, params.toArray());
            return (ArrayList<MessageModel>) msgList;
        } catch (SQLException e) {
            Logger.error(e.getMessage(), e);
        }
        return null;
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

        List<Object> params = new ArrayList<>();
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

    /**
     * 标记为已读
     *
     * @param msgId
     * @return
     */
    public static Integer updateMsgReadme(Long msgId) {

        List<Object> params = new ArrayList<>();
        StringBuffer sqlBuffer = new StringBuffer("update " + MessageModel.TABLE_NAME + " set ");
        sqlBuffer.append(" readme = ?, ");
        params.add(Constants.MessageInfo.README_READED);

        sqlBuffer.append(" update_time = ?");
        params.add(System.currentTimeMillis());

        sqlBuffer.append(" where id = ? ");
        params.add(msgId);
        try {
            return DbHelper.update(sqlBuffer.toString(), params.toArray());
        } catch (SQLException e) {
            Logger.error(e.getMessage(), e);
        }
        return -1;
    }

    public static MessageModel selectMessageDetail(Long msgId) {
        if (msgId == null || msgId <= 0) {
            return null;
        }

        try {
            // sql 使用 as 别名，是为了处理ResultSetHandler数据映射
            String sql = "select id,type,user_from as userFrom,user_to as userTo,content,readme" +
                    ",create_time as createTime,update_time as updateTime from " + MessageModel.TABLE_NAME + " where id = ?";
            List<Object> params = new ArrayList<>();
            params.add(msgId);

            ResultSetHandler<MessageModel> handler = new BeanHandler<>(MessageModel.class);
            return DbHelper.query(sql, handler, params.toArray());
        } catch (SQLException e) {
            Logger.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 查询对话消息
     *
     * @param fromId
     * @param toId
     * @param lastMsgId
     * @return
     */
    public static ArrayList<MessageModel> selectDialogMessages(Long fromId, Long toId, Long lastMsgId) {
        if (fromId == null || fromId <= 0) {
            return null;
        }
        if (toId == null || toId <= 0) {
            return null;
        }

        try {
            // sql 使用 as 别名，是为了处理ResultSetHandler数据映射
            StringBuffer stringBuffer = new StringBuffer("select id,type,user_from as userFrom,user_to as userTo,content,readme,");
            stringBuffer.append(" create_time as createTime,update_time as updateTime from " + MessageModel.TABLE_NAME);
            List<Object> params = new ArrayList<Object>();
            // 用户消息
            stringBuffer.append(" where type = ?");
            params.add(Constants.MessageInfo.MESSAGE_TYPE_USER);

            stringBuffer.append(" and user_from = ?");
            params.add(fromId);

            stringBuffer.append(" and user_to = ?");
            params.add(toId);


            // 向上刷新，查找之前数据，lastMsgId 为最后一条记录id
            if (lastMsgId != null && lastMsgId > 0) {
                stringBuffer.append(" and id < ?");
                params.add(lastMsgId);
            }

            stringBuffer.append(" order by id desc limit ?;");
            params.add(Constants.MessageInfo.PAGE_SIZE);
            ResultSetHandler<List<MessageModel>> handler = new BeanListHandler<>(MessageModel.class);

            List<MessageModel> msgList = DbHelper.query(stringBuffer.toString(), handler, params.toArray());
            return (ArrayList<MessageModel>) msgList;
        } catch (SQLException e) {
            Logger.error(e.getMessage(), e);
        }
        return null;

    }
}
