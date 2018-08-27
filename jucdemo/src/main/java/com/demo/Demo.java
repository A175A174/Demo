package com.demo;

public class Demo {

    public static void main(String[] args) {
        final String[] x = {"A"};
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    while (true){
                        if (x[0].equals(Thread.currentThread().getName())){
                            System.out.println("第"+ (i+1) +"遍");
                            System.out.println(Thread.currentThread().getName());
                            x[0] = "B";
                            break;
                        }
                    }
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    while (true){
                        if (x[0].equals(Thread.currentThread().getName())){
                            System.out.println(Thread.currentThread().getName());
                            x[0] = "C";
                            break;
                        }
                    }
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    while (true){
                        if (x[0].equals(Thread.currentThread().getName())){
                            System.out.println(Thread.currentThread().getName());
                            x[0] = "A";
                            break;
                        }
                    }
                }
            }
        }, "C").start();
    }
}