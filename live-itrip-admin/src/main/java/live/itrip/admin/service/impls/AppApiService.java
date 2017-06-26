package live.itrip.admin.service.impls;

import live.itrip.admin.bean.AppRecyclerItemList;
import live.itrip.admin.service.BaseService;
import live.itrip.admin.service.intefaces.IAppApiService;
import live.itrip.common.ErrorCode;
import live.itrip.common.response.BaseResult;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by 建锋 on 2017/6/26.
 */

@Service
public class AppApiService extends BaseService implements IAppApiService {
    /**
     * 冒泡
     */
    private final int FLAG_BUBBLES = 1;
    /**
     * 收藏
     */
    private final int FLAG_FAVORITE = 2;

    /**
     * 关注
     */
    private final int FLAG_FOLLOWING = 3;
    /**
     * 粉丝
     */
    private final int FLAG_FOLLOWER = 4;

    @Override
    public void selectItemList(HttpServletResponse response, Integer key, String sort, String order, String page, Integer pageSize) {
        AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> result = null;

        switch (key) {
            case FLAG_BUBBLES:
                result = this.selectItemListBubbles(sort, order, page, pageSize);
                break;
            case FLAG_FAVORITE:
                result = this.selectItemListFavorites(sort, order, page, pageSize);
                break;
            case FLAG_FOLLOWING:
                result = this.selectItemListFollowings(sort, order, page, pageSize);
                break;
            case FLAG_FOLLOWER:
                result = this.selectItemListFollowers(sort, order, page, pageSize);
                break;
            default:
                break;
        }
        BaseResult error = new BaseResult();
        if (result == null) {
            error.setCode(ErrorCode.UNKNOWN.getCode());
        } else {
            error.setCode(ErrorCode.SUCCESS.getCode());
        }

        this.writeResponse(response, error);
    }

    private AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> selectItemListBubbles(String sort, String order, String page, Integer pageSize) {
        AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> result = new AppRecyclerItemList<AppRecyclerItemList.RecyclerItem>();

        result.setIncomplete_results(true);
        result.setTotal_count(pageSize);

        ArrayList<AppRecyclerItemList.RecyclerItem> items = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            items.add(new AppRecyclerItemList.RecyclerItem(i + 1, "title" + (i + 1), "desc" + (i + 1), ""));
        }

        result.setItems(items);
        return result;
    }

    private AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> selectItemListFavorites(String sort, String order, String page, Integer pageSize) {
        AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> result = new AppRecyclerItemList<AppRecyclerItemList.RecyclerItem>();

        result.setIncomplete_results(true);
        result.setTotal_count(pageSize);

        ArrayList<AppRecyclerItemList.RecyclerItem> items = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            items.add(new AppRecyclerItemList.RecyclerItem(i + 1, "title" + (i + 1), "desc" + (i + 1), ""));
        }

        result.setItems(items);
        return result;
    }

    private AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> selectItemListFollowings(String sort, String order, String page, Integer pageSize) {
        AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> result = new AppRecyclerItemList<AppRecyclerItemList.RecyclerItem>();

        result.setIncomplete_results(true);
        result.setTotal_count(pageSize);

        ArrayList<AppRecyclerItemList.RecyclerItem> items = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            items.add(new AppRecyclerItemList.RecyclerItem(i + 1, "title" + (i + 1), "desc" + (i + 1), ""));
        }

        result.setItems(items);
        return result;
    }

    private AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> selectItemListFollowers(String sort, String order, String page, Integer pageSize) {
        AppRecyclerItemList<AppRecyclerItemList.RecyclerItem> result = new AppRecyclerItemList<AppRecyclerItemList.RecyclerItem>();

        result.setIncomplete_results(true);
        result.setTotal_count(pageSize);

        ArrayList<AppRecyclerItemList.RecyclerItem> items = new ArrayList<>();
        for (int i = 0; i < pageSize; i++) {
            items.add(new AppRecyclerItemList.RecyclerItem(i + 1, "title" + (i + 1), "desc" + (i + 1), ""));
        }

        result.setItems(items);
        return result;
    }


}
