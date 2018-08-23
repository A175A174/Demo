package com.demo;

import redis.clients.jedis.Jedis;

public class Lock {

    public static Integer is = 0;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (addlock(RedisPool.getJedis(),"l","a")){
                        System.out.println(Thread.currentThread() + "业务a" + is++);
                        dellock(RedisPool.getJedis(),"l","a");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (addlock(RedisPool.getJedis(),"l","b")){
                        System.out.println(Thread.currentThread() + "业务b" + is++);
                        dellock(RedisPool.getJedis(),"l","b");
                    }
                }
            }
        }).start();
    }

    //加锁
    public static boolean addlock(Jedis jedis,String lockk,String lockv){
        String result = jedis.set(lockk, lockv, "NX", "PX", 5000);
        jedis.close();
        if ("OK".equals(result)){
            return true;
        }
        return false;
    }

    //解锁
    public static boolean dellock(Jedis jedis,String lockk,String lockv){
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 'Fail' end";
        Object result = jedis.eval(script, 1, lockk,lockv);
        jedis.close();
        if (!"Fail".equals(result)){
            return true;
        }
        return false;
    }
}
