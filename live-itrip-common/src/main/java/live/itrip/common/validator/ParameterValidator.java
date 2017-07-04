package live.itrip.common.validator;

import java.lang.annotation.*;

/**
 * Created by Feng on 2017/3/28.
 * 注解校验请求报文中 参数合法性
 * 1. timestamp -> sig -> apikey
 * 2. token
 * 3. permission
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParameterValidator {
    String message() default "";

    boolean valParams() default false;
}
