package live.itrip.api.service.impls;

import com.alibaba.fastjson.JSONObject;
import live.itrip.api.service.BaseService;
import live.itrip.api.service.intefaces.IAppHomeService;
import live.itrip.data.model.web.AppHomeItemModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Feng on 2017/7/31.
 */
@Service
public class AppHomeService extends BaseService implements IAppHomeService {
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
    public JSONObject selectHomeList() {
        JSONObject result = new JSONObject();
        result.put("op", "");


        // ITEM_BANNER : banner
        AppHomeItemModel modelBanner = new AppHomeItemModel();
        modelBanner.setId(-10L);
        modelBanner.setImgUrl("");
        modelBanner.setTitle("");
        modelBanner.setContent("");
        modelBanner.setItemType(AppHomeItemModel.ITEM_BANNER);

        ArrayList<AppHomeItemModel.ChildItem> listBannerItems = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            AppHomeItemModel.ChildItem item = new AppHomeItemModel.ChildItem();
            item.setId(i + 100L);
            item.setItemType(AppHomeItemModel.ChildItem.ITEM_PLAN);
            item.setImageUrl(mBannerUrls[i]);
            item.setTitle(mBannerNames[i]);
            listBannerItems.add(item);
        }
        modelBanner.setItems(listBannerItems);
        result.put("banner", modelBanner);

        // ITEM_NAV : 内置导航
        AppHomeItemModel modelNav = new AppHomeItemModel();
        modelNav.setId(-20L);
        modelNav.setItemType(AppHomeItemModel.ITEM_NAV);
        modelNav.setImgUrl("");
        modelNav.setTitle("");
        modelNav.setContent("");
        result.put("nav", modelNav);

        // ITEM_NEW_PLAN ： 最新行程
        AppHomeItemModel modelNewPlan = new AppHomeItemModel();
        modelNewPlan.setId(-30L);
        modelNewPlan.setItemType(AppHomeItemModel.ITEM_NEW_PLAN);
        modelNewPlan.setImgUrl(mUrls[2]);
        modelNewPlan.setTitle(mBannerNames[2]);
        modelNewPlan.setContent(mSubTitles[2]);
        result.put("newPlan", modelNewPlan);

        // ITEM_HOT ： 热门行程
        AppHomeItemModel modeHot = new AppHomeItemModel();
        modeHot.setId(-40L);
        modeHot.setImgUrl("");
        modeHot.setTitle("");
        modeHot.setContent("");
        modeHot.setItemType(AppHomeItemModel.ITEM_HOT);

        ArrayList<AppHomeItemModel.ChildItem> listHot = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AppHomeItemModel.ChildItem item = new AppHomeItemModel.ChildItem();
            item.setId(i + 200L);
            item.setItemType(AppHomeItemModel.ChildItem.ITEM_PLAN);
            item.setImageUrl(mBannerUrls[i]);
            item.setTitle(mBannerNames[i]);
            item.setPrice(10000 + i);
            item.setParticipate(100 + i);
            listHot.add(item);
        }
        modeHot.setItems(listHot);
        result.put("hot", modeHot);

        // ITEM_LIST ： 猜你喜欢
        AppHomeItemModel modelLove = new AppHomeItemModel();
        modelLove.setId(-50L);
        modelLove.setImgUrl("");
        modelLove.setTitle("");
        modelLove.setContent("");
        modelLove.setItemType(AppHomeItemModel.ITEM_LOVE);
        ArrayList<AppHomeItemModel.ChildItem> listLove = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AppHomeItemModel.ChildItem item = new AppHomeItemModel.ChildItem();
            item.setId(i + 300L);
            item.setItemType(AppHomeItemModel.ChildItem.ITEM_PLAN);
            item.setImageUrl(mBannerUrls[i]);
            item.setTitle(mBannerNames[i]);
            item.setPrice(10000 + i);
            item.setParticipate(100 + i);
            listLove.add(item);
        }
        modelLove.setItems(listLove);
        result.put("love", modelLove);

        // ITEM_BLOG ： 热门博客
        AppHomeItemModel modelBlog = new AppHomeItemModel();
        modelBlog.setId(-60L);
        modelBlog.setImgUrl("");
        modelBlog.setTitle("");
        modelBlog.setContent("");
        modelBlog.setItemType(AppHomeItemModel.ITEM_BLOG);
        ArrayList<AppHomeItemModel.ChildItem> listBlog = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AppHomeItemModel.ChildItem item = new AppHomeItemModel.ChildItem();
            item.setId(i + 400L);
            item.setItemType(AppHomeItemModel.ChildItem.ITEM_BOLG);
            item.setImageUrl(mBannerUrls[i]);
            item.setTitle(mBannerNames[i]);
            item.setAuthor("autor " + i);
            item.setToView(500 + i);
            item.setFavorite(300 + 1);
            listBlog.add(item);
        }
        modelBlog.setItems(listBlog);
        result.put("blog", modelBlog);

        // ITEM_AD ： 广告
        AppHomeItemModel modelAd = new AppHomeItemModel();
        modelAd.setId(-70L);
        modelAd.setItemType(AppHomeItemModel.ITEM_AD);
        modelAd.setImgUrl(mUrls[0]);
        modelAd.setTitle(mBannerNames[0]);
        modelAd.setContent(mSubTitles[0]);
        result.put("ad", modelAd);

        result.put("code", 0);
        result.put("msg", "");
        return result;

    }
}
