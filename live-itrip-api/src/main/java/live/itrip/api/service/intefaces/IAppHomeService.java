package live.itrip.api.service.intefaces;

import com.alibaba.fastjson.JSONObject;
import live.itrip.common.response.BaseResult;
import live.itrip.data.model.web.MessageModel;

/**
 * Created by Feng on 2017/6/28.
 */
public interface IAppHomeService {

    JSONObject selectHomeList();
}
