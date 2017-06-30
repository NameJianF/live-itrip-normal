package live.itrip.api.service.impls;

import live.itrip.api.common.Config;
import live.itrip.common.Logger;
import live.itrip.data.common.DataBaseType;
import live.itrip.data.service.InitDataService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Feng on 2016/7/14.
 * <p>
 * 1. 初始化系统配置参数
 */
@Service
public class SystemService {

    public SystemService() {
        initSystem();
    }

    private void initSystem() {
        this.loadConfig();

        this.loadAppVersionConfig();

        try {
            InitDataService.initDBConnection(DataBaseType.Mysql, Config.MysqlConfig.JDBC_URL, Config.MysqlConfig.JDBC_USERNAME, Config.MysqlConfig.JDBC_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        try {
            Properties prop = new Properties();
            String file_name = this.getClass().getClassLoader().getResource("resource/local.properties").getFile();
            prop.load(new FileInputStream(file_name));

            Config.MODULE_APP_APIKEY = prop.getProperty("module.app.apikey");
            Config.MODULE_APP_SECRET = prop.getProperty("module.app.secret");
            Config.SSO_URL = prop.getProperty("sso.url");
            Config.FILE_SAVE_PATH = prop.getProperty("file.save.path");

            Config.MysqlConfig.JDBC_URL = prop.getProperty("jdbc_url");
            Config.MysqlConfig.JDBC_USERNAME = prop.getProperty("jdbc_username");
            Config.MysqlConfig.JDBC_PASSWORD = prop.getProperty("jdbc_password");

        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private void loadAppVersionConfig() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Properties prop = new Properties();
                    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resource/appVersion.properties");
                    BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    prop.load(bf);

                    // android
                    Config.AndroidAppVersion.VERSION_CODE = prop.getProperty("android.version.code");
                    Config.AndroidAppVersion.VERSION_NAME = prop.getProperty("android.version.name");
                    Config.AndroidAppVersion.UPDATE_DESC = prop.getProperty("android.update.desc");
                    Config.AndroidAppVersion.DOWNLOAD_URL = prop.getProperty("android.download.url");
                    Config.AndroidAppVersion.PUBLISH_DATE = prop.getProperty("android.publish.date");

                    Thread.sleep(1000 * 60 * 5);
                } catch (Exception e) {
                    Logger.error(e.getMessage());
                }
            }
        }).start();
    }
}
