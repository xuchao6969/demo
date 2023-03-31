package com.xc.designpattern.singleton;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void mains(String[] args) {
        //饿汉式单例
        HungryUser h1 = HungryUser.getInstance();
        HungryUser h2 = HungryUser.getInstance();
        System.out.println(h1 == h2);

        //懒汉式单例
        LazyUser u1= LazyUser.getInstance();
        LazyUser u2 = LazyUser.getInstance();
        System.out.println(u1==u2);

        StaticUser s1= StaticUser.getInstance();
        StaticUser s2 = StaticUser.getInstance();
        System.out.println(s1 == s2);
    }
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(()->{
                try {
                    System.out.println(StaticUser.getInstance());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
}
