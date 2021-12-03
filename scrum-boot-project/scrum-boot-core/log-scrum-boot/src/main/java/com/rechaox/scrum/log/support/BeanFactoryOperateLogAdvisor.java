package com.rechaox.scrum.log.support;


import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 *
 *
 * @author bingdyee
 * @since 2021/12/02
 */
public class BeanFactoryOperateLogAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private static final long serialVersionUID = 179;

    private final OperateLogPointcut pointcut;

    public BeanFactoryOperateLogAdvisor() {
        this.pointcut = new OperateLogPointcut();
    }

    public void setClassFilter(ClassFilter classFilter) {
        this.pointcut.setClassFilter(classFilter);
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

}
