package com.avaj.ekill.Controller;

import com.avaj.ekill.rabbitmq.test.HelloReceiver1;
import com.avaj.ekill.rabbitmq.test.HelloSender1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    HelloSender1 helloSender1;

    @Autowired
    HelloReceiver1 helloReceiver1;

    @RequestMapping("/test")
    public String testOne() {
        System.out.println("tag");
        helloSender1.send();
        return "sender success";
    }

    @RequestMapping("/test2")
    public String testTwo() {
        System.out.println("tag");
        helloReceiver1.process("hello");
        return "receiver success";
    }
}
