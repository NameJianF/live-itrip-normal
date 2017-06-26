package live.itrip.admin.service.intefaces;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 建锋 on 2017/6/26.
 */
public interface IAppApiService {

    void selectItemList(HttpServletResponse response, Integer key, String sort, String order, String page, Integer pageSize);
}
