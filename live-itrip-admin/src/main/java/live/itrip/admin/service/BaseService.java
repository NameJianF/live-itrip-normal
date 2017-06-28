package live.itrip.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.admin.bean.PagerInfo;
import live.itrip.common.Logger;
import live.itrip.common.response.IResponseWrite;
import live.itrip.common.response.ResponseHelper;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2016/7/7.
 */
public class BaseService implements IResponseWrite {
    @Override
    public void writeResponse(HttpServletResponse response, Object obj) {
        ResponseHelper.writeResponse(response, obj);
    }

    @Override
    public void paramInvalid(HttpServletResponse response, String paramName) {
        ResponseHelper.paramInvalid(response, paramName);
    }

    /**
     * 解析分页查询参数数据
     *
     * @param decodeJson
     * @return
     */
    protected PagerInfo getPagerInfo(String decodeJson) {
        JSONArray jsonarray = JSONArray.parseArray(decodeJson);
        return getPagerInfo(jsonarray);
    }

    /**
     * 解析分页查询参数数据,方法外转换JSONArray,获取自定义参数
     *
     * @param jsonarray
     * @return
     */
    protected PagerInfo getPagerInfo(JSONArray jsonarray) {
        PagerInfo pagerInfo = new PagerInfo();
        try {
            for (int i = 0; i < jsonarray.size(); i++) {
                JSONObject obj = (JSONObject) jsonarray.get(i);
                if (obj.get("name").equals("sEcho")) {
                    pagerInfo.setsEcho(obj.get("value").toString());
                } else if (obj.get("name").equals("iDisplayStart")) {
                    pagerInfo.setiDisplayStart(obj.getInteger("value"));
                } else if (obj.get("name").equals("iDisplayLength")) {
                    pagerInfo.setiDisplayLength(obj.getInteger("value"));
                } else if (obj.get("name").equals("draw")) {
                    pagerInfo.setDraw(obj.getInteger("value"));
                } else if (obj.get("name").equals("columns")) {
                    pagerInfo.setColumns(obj.getJSONArray("value"));
                } else if (obj.get("name").equals("order")) {
                    pagerInfo.setOrder(obj.getJSONArray("value"));
                } else if (obj.get("name").equals("start")) {
                    pagerInfo.setStart(obj.getInteger("value"));
                } else if (obj.get("name").equals("length")) {
                    pagerInfo.setLength(obj.getInteger("value"));
                } else if (obj.get("name").equals("search")) {
                    JSONObject jsonObject = obj.getJSONObject("value");
                    pagerInfo.setSearch(jsonObject.getString("value"));
                } else if (obj.get("name").equals("token")) {
//                    JSONObject jsonObject = obj.getJSONObject("value");
                    pagerInfo.setToken(obj.getString("value"));
                }
            }
        } catch (Exception ex) {
            Logger.error("", ex);
        }
        return pagerInfo;
    }
}
