package com.demo;

/*
 * 一、volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
 * 					  相较于 synchronized 是一种较为轻量级的同步策略。
 * 1. volatile 不具备“互斥性”
 * 2. volatile 不能保证变量的“原子性”
 */
public class TestVolatile {
    public static void main(String[] args) {
        MyThread td = new MyThread();
        new Thread(td).start();
        while (true) {
            synchronized (td){
                if (td.isFlag()) {
                    System.out.println("------------------");
                    break;
                }
            }
//            if (td.isFlag()) {
//                System.out.println("------------------");
//                break;
//            }
        }
    }
}

class MyThread implements Runnable {
    //private boolean flag = false;
    private volatile boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {}
        flag = true;
        System.out.println("flag=" + isFlag());
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}