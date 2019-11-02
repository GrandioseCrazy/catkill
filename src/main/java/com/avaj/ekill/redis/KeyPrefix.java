package com.avaj.ekill.redis;

/**
* @author: DoubleP
* @Date: 2019/11/2 14:44
* @Description: 前缀
*/
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
