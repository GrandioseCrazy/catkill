package com.avaj.ekill.redis.jedis;

import redis.clients.jedis.JedisPool;

public class JedisCache {
    protected JedisPool pool;

    public JedisCache() {}

    public JedisCache(JedisPool pool) {
        this.pool = pool;
    }


}
