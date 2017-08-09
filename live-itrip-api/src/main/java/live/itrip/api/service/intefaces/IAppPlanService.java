package live.itrip.api.service.intefaces;

import com.alibaba.fastjson.JSONObject;
import live.itrip.common.response.BaseResult;

/**
 * Created by Feng on 2017/8/7.
 */
public interface IAppPlanService {

    BaseResult selectPlanDetail(Long planId);

    JSONObject selectRecommendList(String category);
}
