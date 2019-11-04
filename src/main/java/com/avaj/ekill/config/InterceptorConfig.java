package com.avaj.ekill.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 表示拦截所有请求
        String[] addPath = {"/**"};
        // 放行getUserInfo 和 getGoodsInfo
        String[] excludePath = {"/getUserInfo","/getGoodsInfo"};
        registry.addInterceptor(new TestInterceptor()).addPathPatterns(addPath).excludePathPatterns(excludePath);
    }
}
