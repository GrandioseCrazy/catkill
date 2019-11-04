package com.avaj.ekill.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* @author: DoubleP
* @Date: 2019/11/4 8:57
* @Description: 测试的拦截器
*/
public class TestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            // 不是方法的直接放行
            return true;
        }
        System.out.println(request.getRequestURL());
//        if (request.getRequestURL().equals(""))
        System.out.println("interceptor tag");
        return true;
    }
}
