package com.avaj.ekill.service;

import com.avaj.ekill.model.User;

/**
* @author: DoubleP
* @Date: 2019/11/1 14:41
* @Description: 用户登录模板
*/
public abstract class UserLoginTemplate {

    public final boolean login(User record) {
        // 获取用户信息
        User user = this.findLoginUser(record.getLoginCount());

        if (user != null) {
            // 对密码进行加密
            String encryptPwd = this.encryptPwd(record.getPassword());
            user.setPassword(encryptPwd);
            // 比对密码
            return this.match(record,user);
        }
        return false;
    }

    protected abstract boolean match(User record, User user);

    protected abstract User findLoginUser(Integer loginCount);

    public abstract String encryptPwd(String str);

}
