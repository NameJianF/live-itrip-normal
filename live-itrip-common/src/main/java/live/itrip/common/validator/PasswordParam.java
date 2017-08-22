package live.itrip.common.validator;

import live.itrip.common.security.Md5Utils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Feng on 2017/8/21.
 */
public class PasswordParam {
    /**
     * 密码是密文
     */
    public static final String PASSWORD_CIPHERTEXT = "1";
    /**
     * 密码不是密文
     */
    public static final String PASSWORD_NOT_CIPHERTEXT = "0";

    /**
     * 解析密码
     *
     * @param pwd
     * @param salt
     * @param ciphertext
     * @return
     */
    public static String getPassword(String pwd, String salt, String ciphertext) {
        String password = null;
        if (PASSWORD_CIPHERTEXT.equals(ciphertext)) {
            // 密码是密文
            password = pwd;
        } else if (PASSWORD_NOT_CIPHERTEXT.equals(ciphertext)) {
            // 密码明文，md5
            password = Md5Utils.getStringMD5(pwd);
        } else if (StringUtils.isEmpty(ciphertext)) {
            // 空值，默认为明文，md5
            password = Md5Utils.getStringMD5(pwd);
        }
        // 密码加盐处理
        password = Md5Utils.getStringMD5(salt + password);
        return password;
    }
}
