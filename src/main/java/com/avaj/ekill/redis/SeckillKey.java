package com.avaj.ekill.redis;

public class SeckillKey extends BasePrefix {
    public SeckillKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static SeckillKey isGoodsOver =  new SeckillKey(0,"go");
    public static SeckillKey getSeckillPath =  new SeckillKey(60,"sp");
    public static SeckillKey getSeckillVerifyCode =  new SeckillKey(300,"verifyCode");
}
