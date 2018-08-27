package com.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {

    public static void main(String[] args) {
        final int[] x = {1};

        Lock lock = new ReentrantLock();
        Condition conditiona = lock.newCondition();
        Condition conditionb = lock.newCondition();
        Condition conditionc = lock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (x[0] != 1){
                        try {
                            conditiona.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName());
                    x[0] = 2;
                    conditionb.signal();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (x[0] != 2){
                        try {
                            conditionb.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName());
                    x[0] = 3;
                    conditionc.signal();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (x[0] != 3){
                        try {
                            conditionc.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName());
                    x[0] = 1;
                    conditiona.signal();
                }
            }
        }, "C").start();
    }
}