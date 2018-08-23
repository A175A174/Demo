package com.demo;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

public class Lock3 {

    public static Integer is = 0;
    public static RedissonManager redissonManager = new RedissonManager();

    public static void main(String[] args) {
        RLock rlock = redissonManager.getRedisson().getLock("Lock");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    boolean islock = false;
                    try {
                        if (islock = rlock.tryLock(0, 50, TimeUnit.SECONDS)) {
                            System.out.println(Thread.currentThread() + "业务a" + is++);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (islock){
                            rlock.unlock();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    boolean islock = false;
                    try {
                        if (islock = rlock.tryLock(0, 50, TimeUnit.SECONDS)) {
                            System.out.println(Thread.currentThread() + "业务b" + is++);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (islock){
                            rlock.unlock();
                        }
                    }
                }
            }
        }).start();
    }
}
