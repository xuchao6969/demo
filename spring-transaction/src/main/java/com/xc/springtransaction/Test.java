package com.xc.springtransaction;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    static volatile boolean notice = false;
    //停车场同时容纳的车辆10
    private  static Semaphore semaphore=new Semaphore(10);
    public static void main(String[] args) throws InterruptedException {
        semaphoreTest();

    }

    public static void semaphoreTest(){
        //模拟100辆车进入停车场
        for(int i=0;i<100;i++){
            Thread thread=new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println("===="+Thread.currentThread().getName()+"来到停车场");
                        if(semaphore.availablePermits()==0){
                            System.out.println("车位不足，请耐心等待");
                        }
                        semaphore.acquire();//获取令牌尝试进入停车场
                        System.out.println(Thread.currentThread().getName()+"成功进入停车场");
                        Thread.sleep(new Random().nextInt(3000));//模拟车辆在停车场停留的时间
                        System.out.println(Thread.currentThread().getName()+"驶出停车场");
                        semaphore.release();//释放令牌，腾出停车场车位
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },i+"号车");
            thread.start();
        }
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
