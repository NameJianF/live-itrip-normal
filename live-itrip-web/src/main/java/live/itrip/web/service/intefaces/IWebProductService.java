package live.itrip.web.service.intefaces;

import live.itrip.web.model.WebProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Feng on 2016/11/12.
 */
public interface IWebProductService {
    void selectProductList(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    WebProduct selectProductById(Integer productId);

    void editProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void deleteProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectHotProductsByCityId(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectProductListByType(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void selectProductListAbouts(String decodeJson, HttpServletResponse response, HttpServletRequest request);

    void createHtmlProductById(String decodeJson, HttpServletResponse response, HttpServletRequest request) throws IOException;
}
