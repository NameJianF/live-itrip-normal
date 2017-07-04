package live.itrip.data.model.web;

import live.itrip.data.model.BaseModel;

import java.util.Map;

/**
 * Created by Feng on 2017/6/30.
 */
public class MessageModel extends BaseModel {

    public static final String TABLE_NAME = "web_user_message";

    private Long id;
    private String type;
    private Long userFrom;
    private Long userTo;
    private String content;
    private String readme;
    private Long createTime;
    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Long userFrom) {
        this.userFrom = userFrom;
    }

    public Long getUserTo() {
        return userTo;
    }

    public void setUserTo(Long userTo) {
        this.userTo = userTo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
//    public static MessageModel getBean(Map<String, Object> item) {
//        MessageModel bean = new MessageModel();
//        bean.setId((Long) item.get("id"));
//        bean.setType((String) item.get("type"));
//        bean.setUserFrom((Long) item.get("user_from"));
//        bean.setUserTo((Long) item.get("user_to"));
//        bean.setContent((String) item.get("content"));
//        bean.setReadme((String) item.get("readme"));
//        bean.setCreateTime((Long) item.get("create_time"));
//        return bean;
//    }
}
