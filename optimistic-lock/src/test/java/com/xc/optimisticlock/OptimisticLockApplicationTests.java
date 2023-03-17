package com.xc.optimisticlock;

import com.xc.optimisticlock.service.NumberSourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class OptimisticLockApplicationTests {

    @Autowired
    NumberSourceService service;

    @Test
    void bookOrder() throws InterruptedException {
        // 库存初始化为10，这里通过CountDownLatch和线程池模拟100个并发
        int threadTotal = 100;
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
        for (int i = 0; i < threadTotal; i++) {
            int uid = i;
            executorService.execute(()->{
                try {
                    service.register(1,uid);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
        }
        //停止当前线程 使线程中的任务充分执行
        countDownLatch.await();
        executorService.shutdown();
    }

}
