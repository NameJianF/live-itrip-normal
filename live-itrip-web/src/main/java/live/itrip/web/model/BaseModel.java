package live.itrip.web.model;

import java.io.Serializable;

/**
 * Created by Feng on 2016/6/29.
 */
public class BaseModel implements Serializable {

    /**
     * 记录被删除
     */
    public static final String RECORD_IS_DELETE = "1";
    /**
     * 记录正常
     */
    public static final String RECORD_IS_NORMAL = "0";


    /**
     * 该记录是否标记为 删除
     * 1：删除，0：未删除
     */
    private String isDelete;
    /**
     * 记录创建时间
     */
    private Long createTime;
    /**
     * 记录修改时间
     */
    private Long updateTime;

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
