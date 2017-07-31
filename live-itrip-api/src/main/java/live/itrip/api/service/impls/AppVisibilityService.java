package live.itrip.api.service.impls;

import com.alibaba.fastjson.JSONObject;
import live.itrip.api.service.BaseService;
import live.itrip.api.service.intefaces.IAppVisibilityService;
import live.itrip.data.model.web.AppVisibilityItemModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Feng on 2017/7/31.
 */
@Service
public class AppVisibilityService extends BaseService implements IAppVisibilityService {
    // test data
    private static final String[] mUrls = {
            "http://file.juzimi.com/weibopic/jizema7.jpg",
            "http://file.juzimi.com/weibopic/jezime3.jpg",
            "http://file.juzimi.com/weibopic/jizrmx5.jpg",
            "http://file.juzimi.com/weibopic/jlzxma7.jpg",
            "http://file.juzimi.com/weibopic/jlzump.jpg",
            "http://file.juzimi.com/weibopic/jxzlmr2.jpg",
            "http://file.juzimi.com/weibopic/jizdmr.jpg",
            "http://file.juzimi.com/weibopic/jrzpmd7.jpg",
            "http://file.juzimi.com/weibopic/jozemx7.jpg",
            "http://file.juzimi.com/weibopic/jizdmx6.jpg",
            "http://file.juzimi.com/weibopic/jozumo5.jpg",
            "http://file.juzimi.com/weibopic/jozami5.jpg",
            "http://file.juzimi.com/weibopic/jrzdma5.jpg",
            "http://file.juzimi.com/weibopic/jozrmu4.jpg",
            "http://file.juzimi.com/weibopic/jlzpmu5.jpg",
            "http://file.juzimi.com/weibopic/jxzeml6.jpg",
            "http://file.juzimi.com/weibopic/jlzimx4.jpg",
            "http://file.juzimi.com/weibopic/jxzump5.jpg",
            "http://file.juzimi.com/weibopic/jizemo3.jpg",
            "http://file.juzimi.com/weibopic/jdzrme.jpg",
            "http://file.juzimi.com/weibopic/jozemo.jpg"
    };

    private static final String[] mBannerNames = {
            "错过那天那张雨",
            "彼岸花开三色天",
            "基督山伯爵",
            "侄子之手",
            "夏目友人帐",
            "陪我西西里看海",
            "陪我西西里看海",
            "陪我西西里看海",
            "陪我西西里看海",
            "陪我西西里看海"
    };

    private static final String[] mSubTitles = {
            "经得住多大的诋毁，才受得住多大的赞美。",
            "经得住多大的诋毁，才受得住多大的赞美。",
            "经得住多大的诋毁，才受得住多大的赞美。",
            "经得住多大的诋毁，才受得住多大的赞美。",
            "经得住多大的诋毁，才受得住多大的赞美。",
            "陪我到可可西里看一看海，不要未来 只要你来。",
            "陪我到可可西里看一看海，不要未来 只要你来。",
            "陪我到可可西里看一看海，不要未来 只要你来。",
            "陪我到可可西里看一看海，不要未来 只要你来。",
            "陪我到可可西里看一看海，不要未来 只要你来。"
    };
    private static final String[] mBannerUrls = {
            "http://file.juzimi.com/weibopic/jazrmp3.jpg",
            "http://file.juzimi.com/weibopic/jizpma7.jpg",
            "http://file.juzimi.com/weibopic/jxzlmx4.jpg",
            "http://file.juzimi.com/weibopic/jpzame3.jpg",
            "http://file.juzimi.com/weibopic/jizdmu3.jpg",
            "http://file.juzimi.com/weibopic/jizlml6.jpg",
            "http://file.juzimi.com/weibopic/jizlml6.jpg",
            "http://file.juzimi.com/weibopic/jizlml6.jpg",
            "http://file.juzimi.com/weibopic/jizlml6.jpg",
            "http://file.juzimi.com/weibopic/jizlml6.jpg"
    };

    @Override
    public JSONObject selectVisibilityList() {
        JSONObject result = new JSONObject();
        result.put("op", "");


        // ITEM_NAV : 内置导航
        AppVisibilityItemModel modelNav = new AppVisibilityItemModel();
        modelNav.setId(-10L);
        modelNav.setItemType(AppVisibilityItemModel.ITEM_NAV);
        modelNav.setImgUrl("");
        modelNav.setTitle("");
        modelNav.setContent("");
        result.put("nav", modelNav);


        // ITEM_HOT ： 热门行程
        AppVisibilityItemModel modeHot = new AppVisibilityItemModel();
        modeHot.setId(-20L);
        modeHot.setImgUrl(mUrls[2]);
        modeHot.setTitle(mBannerNames[2]);
        modeHot.setContent(mSubTitles[2]);
        modeHot.setItemType(AppVisibilityItemModel.ITEM_HOT);
        result.put("hot", modeHot);

        // ITEM_BLOG ： 精选博客
        AppVisibilityItemModel modelBlog = new AppVisibilityItemModel();
        modelBlog.setId(-30L);
        modelBlog.setImgUrl("");
        modelBlog.setTitle("");
        modelBlog.setContent("");
        modelBlog.setItemType(AppVisibilityItemModel.ITEM_BLOG);
        ArrayList<AppVisibilityItemModel.ChildItem> listBlog = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AppVisibilityItemModel.ChildItem item = new AppVisibilityItemModel.ChildItem();
            item.setId(i + 300L);
            item.setItemType(AppVisibilityItemModel.ChildItem.ITEM_PLAN);
            item.setImageUrl(mBannerUrls[i]);
            item.setTitle(mBannerNames[i]);
            listBlog.add(item);
        }
        modelBlog.setItems(listBlog);
        result.put("blog", modelBlog);


        // ITEM_CATEGORY ： 分类
        AppVisibilityItemModel modelCategory = new AppVisibilityItemModel();
        modelCategory.setId(-40L);
        modelCategory.setImgUrl("");
        modelCategory.setTitle("");
        modelCategory.setContent("");
        modelCategory.setItemType(AppVisibilityItemModel.ITEM_CATEGORY);
        ArrayList<AppVisibilityItemModel.ChildItem> listCategory = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AppVisibilityItemModel.ChildItem item = new AppVisibilityItemModel.ChildItem();
            item.setId(i + 300L);
            item.setItemType(AppVisibilityItemModel.ChildItem.ITEM_PLAN);
            item.setImageUrl(mBannerUrls[i]);
            item.setTitle(mBannerNames[i]);
            listCategory.add(item);
        }
        modelCategory.setItems(listCategory);
        result.put("category", modelCategory);


        // ITEM_AD ： 广告
        AppVisibilityItemModel modelAd = new AppVisibilityItemModel();
        modelAd.setId(-50L);
        modelAd.setItemType(AppVisibilityItemModel.ITEM_AD);
        modelAd.setImgUrl(mUrls[0]);
        modelAd.setTitle(mBannerNames[0]);
        modelAd.setContent(mSubTitles[0]);
        result.put("ad", modelAd);

        result.put("code", 0);
        result.put("msg", "");
        return result;
    }
}
