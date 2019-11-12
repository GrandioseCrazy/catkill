package com.avaj.ekill.controller;

import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.result.Result;
import com.avaj.ekill.service.impl.SeckillUSerService;
import com.avaj.ekill.vo.LoginVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private RedisService redisService;

    @Autowired
    private SeckillUSerService seckillUSerService;

    @ApiOperation("获取登录界面的接口")
    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @ApiOperation("登录接口")
    @ApiImplicitParam()
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVO loginVO) {
        log.info("用户登录" + loginVO.toString());

        // 登录
        String token = seckillUSerService.login(response,loginVO);
        return Result.success(token);
    }
}
