package live.itrip.api.common;

/**
 * Created by Feng on 2017/6/28.
 */
public class Constants {

    public static class AppType {
        public final static String APP_ANDROID = "android";
        public final static String APP_IOS = "iOS";
    }

    public static class PlanType{

        /**
         * 自由行
         */
        public final static String FLAG_SELF_GUIDED = "1";
        /**
         * 跟团游
         */
        public final static String FLAG_GROUP_TRAVEL = "2";
        /**
         * 主题旅游
         */
        public final static String FLAG_THEME_TRAVEL = "3";
        /**
         * 乡村民宿
         */
        public final static String FLAG_COUNTRY_INN = "4";
        /**
         * 旅行服务
         */
        public final static String FLAG_TRAVEL_SERVICE = "5";
    }
}
