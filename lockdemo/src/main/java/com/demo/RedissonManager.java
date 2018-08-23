package com.demo;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;


public class RedissonManager {

    private Config config = new Config();
    private RedissonClient redissonClient = null;

    private void init() {
        try {
            config.useSingleServer().setAddress("redis://192.168.137.8:6379");
            redissonClient = Redisson.create(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RedissonManager() {
        init();
    }

    public RedissonClient getRedisson() {
        return redissonClient;
    }
}
