package com.rechaox.scrum.log.support;

import com.rechaox.scrum.log.annotation.EnableOperateLog;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
public class OperateLogConfigureSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableOperateLog.class.getName()));
        if (annoAttrs != null) {
            return new String[] { OperateLogSupport.class.getName() };
        }
        return new String[0];
    }

}