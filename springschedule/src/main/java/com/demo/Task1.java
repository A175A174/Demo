package com.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task1 {

//    @Scheduled(cron="0 */1 * * * ?")
//    @Scheduled(cron="*/1 * * * * ?")
    public static void kk(){
        Thread thread = Thread.currentThread();
        System.out.println("Hello World!"+thread.getName()+";"+thread.getId());
    }

//    @Scheduled(cron="*/1 * * * * ?")
    public static void kk2() throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println("Hello World22222!"+thread.getName()+";"+thread.getId());
        Thread.sleep(1000000);
    }
}
