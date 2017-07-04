import com.alibaba.fastjson.JSON;
import com.sun.tools.javac.util.Assert;
import live.itrip.common.response.BaseResult;
import live.itrip.sso.bean.UserNameAvatarRequest;
import live.itrip.sso.common.Config;
import live.itrip.sso.common.UserOprations;
import live.itrip.sso.common.exception.ApiException;
import live.itrip.sso.controller.UserController;
import live.itrip.sso.service.interfaces.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * Created by Feng on 2017/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml"})
public class UserTest {
    //    private MockHttpServletRequest request;
//    private MockHttpServletResponse response;
    @Autowired
    private IUserService iUserService;

    @Before
    public void before() {
//        request = new MockHttpServletRequest();
//        request.setCharacterEncoding("UTF-8");
//        response = new MockHttpServletResponse();
//        response.setCharacterEncoding("UTF-8");

        System.err.println("start...");
    }

    @Test
    public void testSelectUserNameAvatar() throws ApiException {
        UserNameAvatarRequest userNameAvatarRequest = new UserNameAvatarRequest();
        userNameAvatarRequest.setOp(UserOprations.OP_API_USER_NAME_AVATAR);
        userNameAvatarRequest.setApikey(Config.MODULE_APP_APIKEY);
        userNameAvatarRequest.setTimestamp(System.currentTimeMillis());


        ArrayList<Long> ids = new ArrayList<>();
        ids.add(4L);
        ids.add(8L);
        userNameAvatarRequest.setIds(ids);

        String json = JSON.toJSONString(userNameAvatarRequest);
        BaseResult baseResult = iUserService.selectUserNameAvatar(json);
        System.err.println(JSON.toJSONString(baseResult));
//        Assert.checkNull(baseResult);
    }
}
