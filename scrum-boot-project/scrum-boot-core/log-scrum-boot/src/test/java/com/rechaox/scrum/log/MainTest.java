package com.rechaox.scrum.log;

import com.rechaox.scrum.log.service.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Bing D. Yee
 * @since 2021/09/25
 */
@SpringBootTest
public class MainTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void test() {
        demoService.test();
    }

}
