package com.avaj.ekill.service.impl;

import com.avaj.ekill.exception.GlobalException;
import com.avaj.ekill.mapper.UserMapper;
import com.avaj.ekill.model.User;
import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.redis.SeckillUSerKey;
import com.avaj.ekill.result.CodeMsg;
import com.avaj.ekill.util.MD5Util;
import com.avaj.ekill.util.UUIDUtil;
import com.avaj.ekill.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class SeckillUSerService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;


    public User getById (long id) {
        // 取缓存
        User user = redisService.get(SeckillUSerKey.getById,"" + id,User.class);
        if (user != null) {
            return user;
        }

        // 取数据库
        user = userMapper.selectByPrimaryKey(id);
        if (user != null) {
            redisService.set(SeckillUSerKey.getById,"" + id,user);
        }
        return user;
    }

    public String login(HttpServletResponse response, LoginVO loginVO) {
        if (loginVO == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVO.getMobile();
        String formPass = loginVO.getPassword();

        User user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        // 验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass,saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response,token,user);

        return token;
        }

    public User getByToken(HttpServletResponse response,String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        User user = redisService.get(SeckillUSerKey.token,token,User.class);

        if (user != null) {
            // 延迟有效时间
            addCookie(response,token,user);
        }

        return user;
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN,token);
        redisService.set(SeckillUSerKey.token,token,user);
        cookie.setMaxAge(SeckillUSerKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
