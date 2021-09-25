package com.rechaox.scrum.log.service.impl;

import com.rechaox.scrum.log.annotation.OperateLogRecord;
import com.rechaox.scrum.log.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
@Service
public class DemoServiceImpl implements DemoService {

    @OperateLogRecord(prefix="sys", module = "测试", action = "方法测试")
    public void test() {
        System.err.println("run test()");
    }

}
