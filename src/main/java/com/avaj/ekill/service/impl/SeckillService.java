package com.avaj.ekill.service.impl;

import com.avaj.ekill.model.OrderInfo;
import com.avaj.ekill.model.User;
import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.service.GoodsService;
import com.avaj.ekill.service.OrderService;
import com.avaj.ekill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeckillService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;

    @Transactional
    public OrderInfo seckill(User user, GoodsVO goodsVO) {
        // 减库存 下
        return null;
    }


}
