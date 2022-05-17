package com.scrumboot.jcolab.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Bing D. Yee
 * @since 2022/05/10
 */
@SuppressWarnings("all")
public class ConcurrentHelper {

    public static void test(Runnable runnable) {
        final ExecutorService executorService = Executors.newFixedThreadPool(8);
        final Semaphore semaphore = new Semaphore(8);
        final CountDownLatch countDownLatch = new CountDownLatch(16);
        for (int i = 0; i < 8; ++i) {
            executorService.execute(runnable);
        }


    }

}
