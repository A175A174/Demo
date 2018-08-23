package com.demo;

import redis.clients.jedis.Jedis;

public class Lock2 {

    public static Integer is = 0;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Thread thread = addlock(RedisPool.getJedis(),"l","a");
                    if (thread != null){
                        System.out.println(Thread.currentThread() + "业务a" + is++);
                        thread.interrupt();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Thread thread = addlock(RedisPool.getJedis(),"l","b");
                    if (thread != null){
                        System.out.println(Thread.currentThread() + "业务b" + is++);
                        thread.interrupt();
                    }
                }
            }
        }).start();
    }

    //加锁和解锁
    public static Thread addlock(Jedis jedis,String lockk,String lockv){
        String result = jedis.set(lockk, lockv, "NX", "PX", 5000);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(4000);
                        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('expire',KEYS[1],5) else return 'Fail' end";
                        jedis.eval(script, 1, lockk,lockv);
                    } catch (Exception e) {
                        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 'Fail' end";
                        jedis.eval(script, 1, lockk,lockv);
                        jedis.close();
                        System.out.println("释放锁");
                        break;
                    }
                }
            }
        });
        if ("OK".equals(result)){
            System.out.println("添加锁");
            thread.start();
            return thread;
        }
        return null;
    }
}
