package com.rechaox.scrum.log.service;

import com.rechaox.scrum.log.OperateLog;
import com.rechaox.scrum.log.Operator;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
public interface OperatorGetService {

    /**
     * 获取当前操作用户
     *
     * @return 操作人
     */
    Operator getCurrentUser();

}
