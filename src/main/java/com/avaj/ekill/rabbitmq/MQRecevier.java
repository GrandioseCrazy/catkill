package com.avaj.ekill.rabbitmq;

import com.avaj.ekill.model.OrderInfo;
import com.avaj.ekill.model.User;
import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.service.GoodsService;
import com.avaj.ekill.service.OrderService;
import com.avaj.ekill.service.impl.SeckillService;
import com.avaj.ekill.vo.GoodsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQRecevier {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillService seckillService;


    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)
    public void receive(String message) {
        System.out.println("receive message" + message);
        SeckillMessage seckillMessage = RedisService.stringToBean(message,SeckillMessage.class);
        // 获取参与秒杀得用户信息
        User user = seckillMessage.getUser();
        long goodsId = seckillMessage.getGoodsId();

        // 判断库存
        GoodsVO goods = goodsService.selectGoodsVOByPrimaryKey(goodsId);
        int stock = goods.getStockCount();
        if (stock < 1) {
            return;
        }

        // 减库存 下订单 写入订单
        OrderInfo seckill = seckillService.seckill(user, goods);

    }
}
