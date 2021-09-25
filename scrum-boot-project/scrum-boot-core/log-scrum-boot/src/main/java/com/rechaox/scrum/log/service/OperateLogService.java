package com.rechaox.scrum.log.service;

import com.rechaox.scrum.log.OperateLog;
import com.rechaox.scrum.log.Operator;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
public interface OperateLogService {

    /**
     * 保存日志
     *
     * @param log
     */
    void saveLog(OperateLog log);

}
