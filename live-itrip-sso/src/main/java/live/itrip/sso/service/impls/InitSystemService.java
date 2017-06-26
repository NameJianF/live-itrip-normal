package live.itrip.sso.service.impls;

import live.itrip.common.Logger;
import live.itrip.sso.api.admin.AdminApi;
import live.itrip.sso.api.admin.bean.ClientApiKey;
import live.itrip.sso.common.Config;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Feng on 2016/7/14.
 * <p>
 * 1. 初始化系统配置参数
 */
@Service
public class InitSystemService {

    private static final Long THREAD_SLEEP = 1000L * 60 * 5;

    public InitSystemService() {
        initSystem();
    }

    private void initSystem() {
        this.loadConfig();

        this.loadListApiKey();
    }

    private void loadConfig() {
        try {
            Properties prop = new Properties();

            String file_name = this.getClass().getClassLoader()
                    .getResource("resource/local.properties").getFile();

            prop.load(new FileInputStream(file_name));

            Config.MODULE_APP_APIKEY = prop.getProperty("module.app.apikey");
            Config.MODULE_APP_SECRET = prop.getProperty("module.app.secret");
            Config.ADMIN_URL = prop.getProperty("admin.url");

        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private void loadListApiKey() {
        final AdminApi adminApi = new AdminApi();
        Thread loadAdminListApiKey = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5 * 1000);

                    adminApi.selectApiKeys();

                    if (Config.LIST_APIKEY != null) {
                        Thread.sleep(THREAD_SLEEP);
                    } else {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    Logger.error(e);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "SSOLoadAdminListApiKey");
        loadAdminListApiKey.start();
    }
}
