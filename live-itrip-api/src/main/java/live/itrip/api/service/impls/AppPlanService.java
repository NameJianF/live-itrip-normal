package live.itrip.api.service.impls;

import com.alibaba.fastjson.JSONObject;
import live.itrip.api.common.Constants;
import live.itrip.api.service.BaseService;
import live.itrip.api.service.intefaces.IAppPlanService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import live.itrip.data.model.web.AppHomeItemModel;
import live.itrip.data.model.web.AppPlanModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Feng on 2017/8/7.
 */
@Service
public class AppPlanService extends BaseService implements IAppPlanService {

    /**
     * 查询行程信息信息
     *
     * @param planId
     * @return
     */
    @Override
    public BaseResult selectPlanDetail(Long planId) {
        BaseResult result = new BaseResult();
        AppPlanModel model = new AppPlanModel();
        if (model != null) {
            // 查询结果
            model.setId(1000L);
            model.setTitle("亲近自然温泉美食本州北海道亲子7日之旅（道央）");
            model.setSubTitle("亲近自然温泉美食本州北海道亲子7日之旅（道央）,亲近自然温泉美食本州北海道亲子7日之旅（道央）,亲近自然温泉美食本州北海道亲子7日之旅（道央）");
            model.setPrice(10000);
            model.setRecommend(AppPlanModel.PLAN_RECOMMEND);
            model.setCommentCount(199);
            model.setTitleImage("http://tourin.cn/upload/images/201706/302f254c900947fb97ab9f7478fd5fd9.jpg");
            model.setParticipate(322);
            model.setHref("http://tourin.cn/view/product/21.html");
            model.setType(Constants.PlanType.FLAG_SELF_GUIDED);

//            // 相关推荐
//            ArrayList<AppPlanModel.Recommend> recommendList = new ArrayList<>();
//            for (int i = 0; i < 5; i++) {
//                AppPlanModel.Recommend item = new AppPlanModel.Recommend();
//                item.setId(i + 1L);
//                item.setTitle("亲近自然温泉美食本州北海道" + i);
//                item.setPrice(2000);
//                item.setImageUrl("http://tourin.cn/upload/images/201706/302f254c900947fb97ab9f7478fd5fd9.jpg");
//                item.setParticipate(299);
//                recommendList.add(item);
//            }
//            model.setRecommendList(recommendList);

            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(model);
            return result;
        }


        result.setError(ErrorCode.UNKNOWN);
        return result;
    }

    @Override
    public JSONObject selectRecommendList(String category) {
        JSONObject result = new JSONObject();
        result.put("op", "");

        ArrayList<AppPlanModel.Recommend> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            AppPlanModel.Recommend item = new AppPlanModel.Recommend();
            item.setId(i + 200L);
            item.setImageUrl(AppHomeService.mBannerUrls[i]);
            item.setTitle(AppHomeService.mBannerNames[i]);
            item.setSubTitle("亲近自然温泉美食本州北海道亲子7日之旅（道央）,亲近自然温泉美食本州北海道亲子7日之旅（道央）,亲近自然温泉美食本州北海道亲子7日之旅（道央）");
            item.setPrice(10000 + i);
            item.setParticipate(100 + i);
            list.add(item);
        }
        result.put("data", list);

        result.put("code", 0);
        result.put("msg", "");
        return result;
    }
}
