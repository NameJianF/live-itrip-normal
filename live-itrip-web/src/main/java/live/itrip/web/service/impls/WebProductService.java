package live.itrip.web.service.impls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import live.itrip.common.ErrorCode;
import live.itrip.common.Logger;
import live.itrip.common.response.BaseResult;
import live.itrip.web.bean.BootStrapDataTableList;
import live.itrip.web.bean.PagerInfo;
import live.itrip.web.common.Constants;
import live.itrip.web.common.HtmlUtils;
import live.itrip.web.common.ViewConstants;
import live.itrip.web.dao.WebProductMapper;
import live.itrip.web.model.WebProduct;
import live.itrip.web.model.WebProductPlan;
import live.itrip.web.service.BaseService;
import live.itrip.web.service.intefaces.IWebFileInfoService;
import live.itrip.web.service.intefaces.IWebProductPlanService;
import live.itrip.web.service.intefaces.IWebProductService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Feng on 2016/11/12.
 */
@Service
public class WebProductService extends BaseService implements IWebProductService {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private WebProductMapper webProductMapper;
    @Autowired
    private IWebFileInfoService iWebFileInfoService;
    @Autowired
    private IWebProductPlanService iWebProductPlanService;


    @Override
    public void selectProductList(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BootStrapDataTableList<WebProduct> result = new BootStrapDataTableList<WebProduct>();
        try {
            PagerInfo pagerInfo = this.getPagerInfo(decodeJson);

            Subject subject = SecurityUtils.getSubject();
            subject.isPermitted();

            Integer count = webProductMapper.countAll();
            List<WebProduct> moduleList = webProductMapper.selectProducts(pagerInfo.getStart(), pagerInfo.getLength());
            if (moduleList != null) {
                result.setsEcho(String.valueOf(pagerInfo.getDraw() + 1));
                result.setiTotalRecords(count);
                result.setiTotalDisplayRecords(count);
                result.setAaData(moduleList);

                // response
                this.writeResponse(response, result);
                return;
            }
        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }

        BaseResult error = new BaseResult();
        error.setCode(ErrorCode.UNKNOWN.getCode());
        this.writeResponse(response, error);
    }

    @Override
    public void selectProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer productId = jsonObject.getInteger("productId");
        if (productId != null) {
            WebProduct info = this.webProductMapper.selectByPrimaryKey(productId);
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(info);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public WebProduct selectProductById(Integer productId) {
        return this.webProductMapper.selectByPrimaryKey(productId);
    }

    @Override
    public void editProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        WebProduct info = JSON.parseObject(decodeJson, WebProduct.class);
        Integer ret;
        if (info.getId() == null) {
            // new
            info.setCreateTime(System.currentTimeMillis());
            info.setUpdateTime(info.getCreateTime());
            ret = this.webProductMapper.insertSelective(info);
            if (ret > 0) {
                // 更新文件表数据
                iWebFileInfoService.modifyFileInfoById(decodeJson, info.getId());

                result.setCode(ErrorCode.SUCCESS.getCode());
                JSONObject object = new JSONObject();
                object.put("id", info.getId());
                result.setData(object);

                this.writeResponse(response, result);
                return;
            }
        } else {
            // update
            info.setUpdateTime(System.currentTimeMillis());
            ret = this.webProductMapper.updateByPrimaryKeySelective(info);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    @Override
    public void deleteProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer productId = (Integer) jsonObject.get("productId");
        if (productId != null) {
            WebProduct info = new WebProduct();
            info.setId(productId);
            info.setIsDelete(Constants.FLAG_IS_DELETE);
            Integer ret = this.webProductMapper.updateByPrimaryKeySelective(info);
            if (ret > 0) {
                result.setCode(ErrorCode.SUCCESS.getCode());
                result.setData(info);
                this.writeResponse(response, result);
                return;
            }
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    /**
     * 根据 城市 查询相关推荐产品
     * <p/>
     * 1. 查询城市相关的产品
     * 2. 按照点击 参与人数倒序排列
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectHotProductsByCityId(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
//        Integer cityId = jsonObject.getInteger("cityId");
        String cityName = jsonObject.getString("cityName");

        if (StringUtils.isEmpty(cityName)) {
            cityName = "东京";
        }
        int topCount = 5;
        List<WebProduct> list = this.webProductMapper.selectHotProductsByCity(topCount, cityName);

        if (list == null || list.size() == 0) {
            list = this.webProductMapper.selectHotProductsByCity(topCount, "");
        }

        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setData(list);
        this.writeResponse(response, result);
    }

    /**
     * 查询产品相关产品列表
     * <p/>
     * 1. 查询同产品类型下的其他产品
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void selectProductListAbouts(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        Integer productId = jsonObject.getInteger("productId");
        String productType = jsonObject.getString("productType");

        if (productId == null) {
            productId = 0;
        }
        if (StringUtils.isEmpty(productType)) {
            productType = ViewConstants.ProductType.Free;
        }
        int topCount = 5;
        List<WebProduct> list = this.webProductMapper.selectListAbouts(topCount, productId, productType);

        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setData(list);
        this.writeResponse(response, result);
    }

    @Override
    public void selectProductListByType(String decodeJson, HttpServletResponse response, HttpServletRequest request) {
        BaseResult result = new BaseResult();
        JSONObject jsonObject = JSON.parseObject(decodeJson);
        String params = jsonObject.getString("params");
        List<WebProduct> list = null;
        String theme = "";
        String city = "";

        if (StringUtils.isNotEmpty(params)) {
            // set params
            JSONArray array = JSON.parseArray(params);
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                if ("theme".equalsIgnoreCase(object.getString("id"))) {
                    JSONArray tmp = object.getJSONArray("ValueList");
                    theme = tmp.toString().replace("[", "").replace("]", "").replaceAll("\"", "");
                }
                if ("citys".equalsIgnoreCase(object.getString("id"))) {
                    JSONArray tmp = object.getJSONArray("ValueList");
                    city = tmp.toString().replace("[", "").replace("]", "").replaceAll("\"", "");
                }
            }
        }

        list = this.webProductMapper.selectListByTypeAndCity(theme, city);


        if (list != null) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setData(list);
            this.writeResponse(response, result);
            return;
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);
    }

    /**
     * 生成静态html文件
     *
     * @param decodeJson
     * @param response
     * @param request
     */
    @Override
    public void createHtmlProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws IOException {
        BaseResult result = new BaseResult();
        String rootPath = servletContext.getRealPath("");

        Integer pid = Integer.valueOf(request.getParameter("pid"));
        if (pid == null) {
            result.setError(ErrorCode.UNKNOWN);
            result.setMsg("Pid Error.");
            this.writeResponse(response, result);
            return;
        }

        String url = "/view/product/" + pid + ".html";
        String htmlPath = rootPath + url;
        File file = new File(htmlPath);
        if (file.exists()) {
            // html 文件存在,删除后重新生成
            file.delete();
        }

        //生成html文件
        try {
            WebProduct product = this.selectProductById(pid);
            if (product == null) {
                response.sendRedirect("/productError.html");
                return;
            }
            List<WebProductPlan> planList = iWebProductPlanService.selectPlanList(pid);

            servletContext.setAttribute("product", product);
            servletContext.setAttribute("planList", planList);

            String jspPath = "/pages/view/template/prod.jsp";
            HtmlUtils.createHtmlFile(servletContext, request, response, jspPath, htmlPath);

            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setMsg(htmlPath);
            this.writeResponse(response, result);

        } catch (Exception ex) {
            Logger.error(ex.getMessage(), ex);
        }

        result.setError(ErrorCode.UNKNOWN);
        this.writeResponse(response, result);

    }
}
