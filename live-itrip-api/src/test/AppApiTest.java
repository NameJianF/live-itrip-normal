import com.alibaba.fastjson.JSON;
import live.itrip.api.service.intefaces.IUserMessageService;
import live.itrip.common.response.BaseResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Feng on 2017/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
public class AppApiTest {


    @Autowired
    private IUserMessageService iUserMessageService;

    @Before
    public void before() {

    }

    @Test
    public void testSelectDialogMessages() {
        BaseResult result = iUserMessageService.selectDialogMessages(11L, 1L, 0L);
        System.err.println(JSON.toJSONString(result));
    }
}
