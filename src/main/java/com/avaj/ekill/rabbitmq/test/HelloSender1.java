package com.avaj.ekill.rabbitmq.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender1 {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     用于单生产者 单消费者的测试
     **/
    public void send() {
        String sendMsg = "hello1" + new Date();
        System.out.println("sender1" + sendMsg);
        this.rabbitTemplate.convertAndSend("helloQueue",sendMsg);
    }


}
