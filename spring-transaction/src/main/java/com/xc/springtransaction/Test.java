package com.xc.springtransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    static volatile boolean notice = false;
    public static void main(String[] args) throws InterruptedException {
        lockSupportTest();
    }

    public static void volatileTest() throws InterruptedException{
        List<String> list = new ArrayList<>();
        Thread threadA = new Thread(()->{
            for (int i = 0; i < 7; i++) {
                list.add("abc");
                System.out.println("threadA----"+ list.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (4==list.size()){
                    notice = true;
                }
            }
        });

        Thread threadB = new Thread(()->{
            while (true){
                if (4 != list.size()){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (notice){
                    System.out.println("threadB*****"+list.size());
                    break;
                }
            }
        });

        threadB.start();
        Thread.sleep(1000);
        threadA.start();
    }

    public static void waitNotifyTest() throws InterruptedException{
        Object lock = new Object();
        List<String> list = new ArrayList<>();
        Thread threadA = new Thread(()->{
            synchronized (lock){
                for (int i = 0; i < 7; i++) {
                    list.add("abc");
                    System.out.println("threadA----"+ list.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (4==list.size()){
                        lock.notify();
                    }
                }
            }

        });

        Thread threadB = new Thread(()->{
            while (true){
                synchronized (lock){
                    if (4 != list.size()){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("threadB*****"+list.size());
                }
            }

        });

        threadB.start();
        Thread.sleep(1000);
        threadA.start();
    }

    public static void countDownLatchTest() throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(1);
        List<String> list = new ArrayList<>();
        Thread threadA = new Thread(()->{
            for (int i = 0; i < 7; i++) {
                list.add("abc");
                System.out.println("threadA---"+list.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (4 == list.size()){
                    countDown.countDown();
                }
            }
        });

        Thread threadB = new Thread(()->{
           while(true){
               if (4 != list.size()){
                   try {
                       countDown.await();
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
                   ;
               }
               System.out.println("threadB****"+list.size());
               break;
           }
        });
        threadB.start();
        Thread.sleep(1000);
        threadA.start();
    }

    public static void reentranLockTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<String> list = new ArrayList<>();
        Thread threadA = new Thread(()->{
            lock.lock();
            for (int i = 0; i < 7; i++) {
                list.add("abc");
                System.out.println("threadA---"+list.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (4 == list.size()){
                    condition.signal();
                }
            }
            lock.unlock();
        });

        Thread threadB = new Thread(()->{
            lock.lock();
            while(true){
                if (4 != list.size()){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("threadB****"+list.size());
                break;
            }
            lock.unlock();
        });
        threadB.start();
        Thread.sleep(1000);
        threadA.start();
    }

    public static void lockSupportTest() throws InterruptedException{
        List<String> list = new ArrayList<>();

        Thread threadB = new Thread(()->{
            while(true){
                if (4 != list.size()){
                    LockSupport.park();
                }
                System.out.println("threadB****"+list.size());
                break;
            }
        });

        Thread threadA = new Thread(()->{
            for (int i = 0; i < 7; i++) {
                list.add("abc");
                System.out.println("threadA----"+ list.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (4 == list.size()){
                    LockSupport.unpark(threadB);
                }
            }
        });
        threadB.start();
        threadA.start();
    }
}
