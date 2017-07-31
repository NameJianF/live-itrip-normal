package live.itrip.data.model.web;

import java.util.ArrayList;

/**
 * Created by Feng on 2017/7/31.
 */
public class AppVisibilityItemModel {
    public static final int ITEM_NAV = 1;
    public static final int ITEM_HOT = 2;
    public static final int ITEM_CATEGORY = 3;
    public static final int ITEM_BLOG = 4;
    public static final int ITEM_AD = 5;


    private Long id;
    private String title;
    private String content;
    private Long createTime;
    private String type; // 记录类型
    private String imgUrl; // 图片地址
    private int itemType;

    private ArrayList<ChildItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public ArrayList<ChildItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ChildItem> items) {
        this.items = items;
    }

    public static class ChildItem {
        public static final int ITEM_PLAN = 1;
        public static final int ITEM_BOLG = 2;

        private Long id;
        private String title;
        private String imageUrl;
        private int itemType;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getItemType() {
            return itemType;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }
}
