package com.avaj.ekill.rabbitmq.test;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class HelloReceiver1 {

    @RabbitHandler
    @RabbitListener(queues = "helloQueue")
    public void process(String hello) {
        System.out.println("我已经在接收数据啦！");
        System.out.println("Receiver1 : " + hello);
    }
}
