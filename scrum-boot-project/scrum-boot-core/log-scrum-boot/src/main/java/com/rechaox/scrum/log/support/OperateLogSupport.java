package com.rechaox.scrum.log.support;

import com.rechaox.scrum.log.annotation.OperateLogRecord;
import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.beans.BeansException;

import java.lang.reflect.Method;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
public class OperateLogSupport extends AbstractAutoProxyCreator {

    private final Advisor advisor = new DefaultIntroductionAdvisor(new OperateLogInterceptor());

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        return new Object[]{advisor};
    }

    @Override
    protected boolean shouldSkip(Class<?> beanClass, String beanName) {
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            OperateLogRecord txAnnotation = method.getAnnotation(OperateLogRecord.class);
            if (txAnnotation != null) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

}
