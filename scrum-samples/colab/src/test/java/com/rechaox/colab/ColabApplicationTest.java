package com.rechaox.colab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * @author Bing D. Yee
 * @since 2022/01/07
 */
@SpringBootTest(classes = ColabApplication.class)
public class ColabApplicationTest {

    @Autowired
    private ApplicationContext app;

    @Test
    public void one() {
        Object bean = app.getBean("&demo");
        System.err.println(bean);
    }

}
