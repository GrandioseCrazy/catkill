package com.avaj.ekill.access;

import com.avaj.ekill.model.User;

/**
* @author: DoubleP
* @Date: 2019/11/11 17:46
* @Description: 线程局部变量
*/
public class UserContext {

    private static ThreadLocal<User> userHolder = new ThreadLocal<>();

    public static void setUser(User user) {
        userHolder.set(user);
    }

    public static User getUser() {
        return userHolder.get();
    }

}
