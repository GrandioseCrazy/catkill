package com.avaj.ekill.redis;
/**
* @author: DoubleP
* @Date: 2019/11/2 15:21
* @Description: userKey
*/
public class UserKey extends BasePrefix{

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");

}
