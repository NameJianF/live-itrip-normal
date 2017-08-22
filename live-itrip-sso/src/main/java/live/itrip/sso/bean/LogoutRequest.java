package live.itrip.sso.bean;

import live.itrip.common.request.RequestHeader;

/**
 * Created by Feng on 2016/3/15.
 * <p>
 * logout
 */
public class LogoutRequest extends RequestHeader {
    private Long uid;   // 用户uid
    private String email; // email
    private String token; // token


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
