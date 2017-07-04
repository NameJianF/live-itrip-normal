package live.itrip.sso.bean;

import live.itrip.common.request.RequestHeader;

import java.util.ArrayList;

/**
 * Created by Feng on 2017/7/3.
 */
public class UserNameAvatarRequest extends RequestHeader {

    private ArrayList<Long> ids;

    public ArrayList<Long> getIds() {
        return ids;
    }

    public void setIds(ArrayList<Long> ids) {
        this.ids = ids;
    }
}
