package controller;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    //jedis连接池
    private static JedisPool pool;

    //最大连接数
    private static Integer maxTotal = 20;

    //在jedispool中最大的idle状态(空闲的)的jedis实例的个数
    private static Integer maxIdle = 20;

    //在jedispool中最小的idle状态(空闲的)的jedis实例的个数
    private static Integer minIdle = 20;

    //在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。
    private static Boolean testOnBorrow = true;

    //在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。
    private static Boolean testOnReturn = true;

    private static String redisIp = "192.168.8.10";
    private static Integer redisPort = 6379;

    /**
     * 初始化连接池
     */
    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        //连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
        config.setBlockWhenExhausted(true);
        pool = new JedisPool(config, redisIp, redisPort, 1000 * 2);
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
        Jedis jedis = pool.getResource();
        jedis.set("kekeke", "veveveveve");
        returnResource(jedis);
        pool.destroy();//销毁连接池中的所有连接
        System.out.println("end");
    }
}
