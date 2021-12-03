package com.rechaox.scrum.log.support;

import com.rechaox.scrum.log.annotation.OperateLogRecord;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 *
 * @author bingdyee
 * @since 2021/12/02
 */
public class OperateLogPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return AnnotationUtils.getAnnotation(method, OperateLogRecord.class) != null;
    }

}
