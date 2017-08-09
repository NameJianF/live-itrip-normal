package live.itrip.data.model.web;

import live.itrip.data.model.BaseModel;

import java.util.ArrayList;

/**
 * Created by Feng on 2017/8/7.
 */
public class AppPlanModel extends BaseModel {
    /**
     * 推荐
     */
    public static final String PLAN_RECOMMEND = "1";

    private Long id;
    private String title;   // 标题
    private String subTitle;  // 副标，保留
    private Integer price;  // 价格
    //    private Integer score; // 评分
    private String recommend; // 推荐：1，未推荐：0
    private String titleImage; // 图片
    private int participate; // 销售数量
    private int commentCount; // 评论总数

    private String href = "";
    private String type;  // 行程类型

    // 相关推荐
//    private ArrayList<Recommend> recommendList;

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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    public int getParticipate() {
        return participate;
    }

    public void setParticipate(int participate) {
        this.participate = participate;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public ArrayList<Recommend> getRecommendList() {
//        return recommendList;
//    }
//
//    public void setRecommendList(ArrayList<Recommend> recommendList) {
//        this.recommendList = recommendList;
//    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 相关推荐
     */
    public static class Recommend {
        private Long id;
        private String title;
        private String subTitle;
        private String imageUrl;

        // 行程采用
        private Integer price; // 价格
        private Integer participate; // 参与人数

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

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public Integer getParticipate() {
            return participate;
        }

        public void setParticipate(Integer participate) {
            this.participate = participate;
        }
    }
}
