package com.avaj.ekill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author: DoubleP
* @Date: 2019/11/2 13:28
* @Description: 用户
*/
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("/doLogin")
    public String login(){
        return null;
    }
}
