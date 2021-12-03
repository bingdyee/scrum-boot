package com.rechaox.scrum.log.support;

import com.rechaox.scrum.log.annotation.OperateLogRecord;
import com.rechaox.scrum.log.configuration.OperateLogAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
public class OperateLogConfigureSelector extends AdviceModeImportSelector<OperateLogRecord> {

    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        switch (adviceMode) {
            case PROXY:
            case ASPECTJ:
                return new String[] { OperateLogAutoConfiguration.class.getName() };
            default:
                return null;
        }
    }

}