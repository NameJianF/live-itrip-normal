package live.itrip.api.service.intefaces;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Feng on 2017/6/28.
 */
public interface IUserMessageService {

    void selectMessageList(HttpServletResponse response, Long userId, int page, int pageSize, Long lastMsgId);

}
