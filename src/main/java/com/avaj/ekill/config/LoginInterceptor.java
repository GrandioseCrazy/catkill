package com.avaj.ekill.config;

import com.avaj.ekill.exception.GlobalException;
import com.avaj.ekill.result.CodeMsg;
import com.avaj.ekill.service.impl.SeckillUSerService;
import com.avaj.ekill.util.CookieUtil;
import com.avaj.ekill.validator.NeedLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Service
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private SeckillUSerService seckillUSerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        NeedLogin needLogin = method.getAnnotation(NeedLogin.class);

        if (needLogin != null) {
            String paramToken = request.getParameter(SeckillUSerService.COOKIE_NAME_TOKEN);
            String cookieToken = CookieUtil.getCookieValue(request,SeckillUSerService.COOKIE_NAME_TOKEN);

            if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
                return false;
            }
            String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
            if (seckillUSerService.getByToken(response,token) == null) {
                throw new GlobalException(CodeMsg.SESSION_ERROR);
            }
            return true;
        }
        return true;
    }
}
