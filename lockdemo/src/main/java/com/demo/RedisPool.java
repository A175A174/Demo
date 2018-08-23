package com.demo;

import redis.clients.jedis.*;

public class RedisPool {
    //jedis连接池
    private static JedisPool pool;

    /**
     * 初始化连接池
     */
    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(20);
        config.setMinIdle(20);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        //连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true
        config.setBlockWhenExhausted(true);
        pool = new JedisPool(config,"192.168.137.8");
    }

    static {
        initPool();
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        //todo close
        jedis.close();
    }

    public static void main(String[] args) {
        RedisPool.getJedis().set("xxxasa", "xasasdasd", "NX", "PX", 1000000);
    }
}
