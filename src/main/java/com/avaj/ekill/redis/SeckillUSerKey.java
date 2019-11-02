package com.avaj.ekill.redis;

public class SeckillUSerKey extends BasePrefix {
    public static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public SeckillUSerKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SeckillUSerKey token = new SeckillUSerKey(TOKEN_EXPIRE,"tk");
    public static SeckillUSerKey getById = new SeckillUSerKey(0,"id");
}
