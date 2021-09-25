package com.rechaox.scrum.log.annotation;

import java.lang.annotation.*;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface OperateLogRecord {

    String prefix();

    String module();

    String action();

    String succeed() default "";

    String failed() default "";

    String condition() default "";

}
