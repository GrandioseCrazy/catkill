package com.avaj.ekill.access;

import com.alibaba.fastjson.JSON;
import com.avaj.ekill.model.User;
import com.avaj.ekill.redis.AccessKey;
import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.result.CodeMsg;
import com.avaj.ekill.result.Result;
import com.avaj.ekill.service.impl.SeckillService;
import com.avaj.ekill.service.impl.SeckillUSerService;
import com.avaj.ekill.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SeckillUSerService userService;

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果请求的是个方法
        if (handler instanceof HandlerMethod) {
            User user = getUser(request,response);
            UserContext.setUser(user);
            // 获取方法上的注解
            HandlerMethod method = (HandlerMethod)handler;
            AccessLimit accessLimit = method.getMethodAnnotation(AccessLimit.class);
            // 没有注解那就不限流
            if (accessLimit == null) {
                return true;
            }

            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if (needLogin) {
                if (user == null) {
                    render(response, CodeMsg.SERVER_ERROR);
                    return false;
                }
                key += "_" + user.getId();
            }

            // 查询访问的次数
            AccessKey accessKey = AccessKey.withExpire(seconds);
            // 查询访问的次数
            Integer count = redisService.get(accessKey,key,Integer.class);
            if (count == null) {
                redisService.set(accessKey,key,1);
            } else if (count < maxCount) {
                redisService.incr(accessKey,key);
            } else {
                render(response,CodeMsg.ACCESS_LIMIT_REACHED);
                return false;
            }
        }
        return true;
    }

    private void render(HttpServletResponse response, CodeMsg serverError) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        String str = JSON.toJSONString(Result.error(serverError));
        outputStream.write(str.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }

    private User getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramTOken = request.getParameter(SeckillUSerService.COOKIE_NAME_TOKEN);
        String cookieToken = CookieUtil.getCookieValue(request,SeckillUSerService.COOKIE_NAME_TOKEN);

        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramTOken)) {
            return null;
        }

        String token = StringUtils.isEmpty(paramTOken) ? cookieToken : paramTOken;
        return userService.getByToken(response,token);
    }
}
