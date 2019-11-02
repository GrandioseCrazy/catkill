package com.avaj.ekill.redis;
/**
* @author: DoubleP
* @Date: 2019/11/2 15:08
* @Description: orderKey
*/
public class OrderKey extends BasePrefix {
    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getSeckillOrderByUidGid = new OrderKey("soug");
}
