package live.itrip.sso.bean;

/**
 * 登录请求
 *
 * @author JianF
 */
public class LoginRequest {
    private String op; //
    private String apikey; // 第三方APP的唯一标识
    private Long timestamp; // 时间戳
    private String sig; // 数字签名
    private String clientVersion; // 客户端版本
    private String source;// 来源包括第三方


    public LoginData data;

    public LoginRequest() {

    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public static class LoginData {
        private String email;// 用户帐号
        private String password;// 用户密码
        private String uid;   // 第三方登录的uid
        /**
         * 是：1，否：0
         */
        private String ciphertext;// 密码是否是密文

        public LoginData() {
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCiphertext() {
            return ciphertext;
        }

        public void setCiphertext(String ciphertext) {
            this.ciphertext = ciphertext;
        }
    }
}
