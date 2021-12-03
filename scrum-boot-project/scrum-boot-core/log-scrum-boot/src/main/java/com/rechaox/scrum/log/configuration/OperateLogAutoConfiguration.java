package com.rechaox.scrum.log.configuration;

import com.rechaox.scrum.log.Operator;
import com.rechaox.scrum.log.annotation.EnableOperateLog;
import com.rechaox.scrum.log.service.OperateLogService;
import com.rechaox.scrum.log.service.OperatorGetService;
import com.rechaox.scrum.log.support.BeanFactoryOperateLogAdvisor;
import com.rechaox.scrum.log.support.OperateLogInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
@Configuration
public class OperateLogAutoConfiguration implements ImportAware {

    private AnnotationAttributes enableOperateLog;

    @Bean
    public OperateLogInterceptor operateLogInterceptor() {
        return new OperateLogInterceptor();
    }

    public BeanFactoryOperateLogAdvisor beanFactoryOperateLogAdvisor() {
        BeanFactoryOperateLogAdvisor advisor = new BeanFactoryOperateLogAdvisor();
        advisor.setAdvice(operateLogInterceptor());
        return advisor;
    }

    @Bean
    @ConditionalOnMissingBean(OperateLogService.class)
    public OperateLogService operateLogService() {
       return log -> System.err.println("saveLog");
    }

    @Bean
    @ConditionalOnMissingBean(OperatorGetService.class)
    public OperatorGetService operatorGetService() {
        return Operator::new;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.enableOperateLog = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableOperateLog.class.getName(), false));
        if (this.enableOperateLog == null) {
            //log.info("@EnableCaching is not present on importing class");
        }
    }

}
