package com.rechaox.scrum.log.support;

import com.google.common.base.Preconditions;
import com.rechaox.scrum.log.OperateLog;
import com.rechaox.scrum.log.annotation.OperateLogRecord;
import com.rechaox.scrum.log.context.OperateLogContext;
import com.rechaox.scrum.log.service.OperateLogService;
import com.rechaox.scrum.log.service.OperatorGetService;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * DATE 5:39 PM
 *
 * @author mzt.
 */
@Slf4j
public class OperateLogInterceptor implements BeanFactoryAware, InitializingBean, MethodInterceptor, Serializable {

    private BeanFactory beanFactory;

    private OperateLogService operateLogService;

    private OperatorGetService operatorGetService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        MethodExecuteResult result = new MethodExecuteResult(true, null, "");
        OperateLogContext.putEmptySpan();
        Object ret = null;
        long rt = 0;
        try {
            long start = System.currentTimeMillis();
            ret = invocation.proceed();
            rt = System.currentTimeMillis() - start;
        } catch (Exception e) {
            result = new MethodExecuteResult(false, e, e.getMessage());
        }
        try {
            Class<?> targetClass = AopProxyUtils.ultimateTargetClass(invocation.getThis());
            Method specificMethod = ClassUtils.getMostSpecificMethod(invocation.getMethod(), targetClass);
            final Method method = BridgeMethodResolver.findBridgedMethod(specificMethod);
            OperateLogRecord logRecord = AnnotationUtils.getAnnotation(method, OperateLogRecord.class);
            if (logRecord != null) {
                OperateLog operateLog = OperateLog.builder()
                        .action(logRecord.action())
                        .module(logRecord.module())
                        .responseTime(rt)
                        .createTime(LocalDateTime.now())
                        .build();

                // 保存日志
                System.err.println("save log: " + operateLog);
            }
        } catch (Exception t) {
            //记录日志错误不要影响业务
            System.err.println("log record parse exception" + t);
        } finally {
            OperateLogContext.clear();
        }
        if (result.getThrowable() != null) {
            throw result.getThrowable();
        }
        return ret;
    }

    public void setOperateLogService(OperateLogService operateLogService) {
        this.operateLogService = operateLogService;
    }

    public void setOperatorGetService(OperatorGetService operatorGetService) {
        this.operatorGetService = operatorGetService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        operateLogService = beanFactory.getBean(OperateLogService.class);
        operatorGetService = beanFactory.getBean(OperatorGetService.class);
        Preconditions.checkNotNull(operateLogService, "bizLogService not null");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

}
