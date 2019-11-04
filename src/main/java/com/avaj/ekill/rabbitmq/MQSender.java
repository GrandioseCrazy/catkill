package com.avaj.ekill.rabbitmq;

import com.avaj.ekill.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendSeckillMessage(SeckillMessage message) {
        String msg = RedisService.beanToString(message);
        log.info("send message: {}" ,message);
        amqpTemplate.convertAndSend(MQConfig.SECKILL_QUEUE,msg);
    }
}
