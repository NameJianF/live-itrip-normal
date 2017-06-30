package live.itrip.data.service;

import live.itrip.data.common.DataBaseType;
import live.itrip.data.db.DbHelper;

import java.sql.SQLException;

/**
 * Created by Feng on 2017/6/30.
 */
public class InitDataService {

    public static void initDBConnection(DataBaseType dataBaseType, String jdbcUrl, String jdbcUserName, String jdbcPwd) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        DbHelper.initConnection(dataBaseType, jdbcUrl, jdbcUserName, jdbcPwd);
    }
}
