package com.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Demo3 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> js = new Js(1L,100L);
        Long restul = pool.invoke(js);
        System.out.println(restul);
    }
}

class Js extends RecursiveTask<Long>{

    private Long a;
    private Long b;

    public Js(Long a, Long b) {
        this.a = a;
        this.b = b;
    }

    @Override
    protected Long compute() {
        if (b-a < 10) {
            long sum = 0;
            for (Long i = a; i <= b; i++) {
                sum += i;
            }
            return sum;
        } else {
            Js left = new Js(a, (a+b)/2);
            left.fork(); //进行拆分，同时压入线程队列
            Js right = new Js((a+b)/2+1, b);
            right.fork(); //进行拆分，同时压入线程队列
            return left.join() + right.join();
        }
    }
}
