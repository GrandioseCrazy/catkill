package com.avaj.ekill.service.impl;

import com.avaj.ekill.mapper.OrderInfoMapper;
import com.avaj.ekill.model.OrderInfo;
import com.avaj.ekill.model.OrderKill;
import com.avaj.ekill.model.User;
import com.avaj.ekill.redis.OrderKey;
import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.service.OrderService;
import com.avaj.ekill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderInfo record) {
        return orderInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderInfo record) {
        return orderInfoMapper.insertSelective(record);
    }

    @Override
    public OrderInfo selectByPrimaryKey(Long id) {
        return orderInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderInfo record) {
        return orderInfoMapper.updateByPrimaryKey(record);
    }
    /**
     * @Author DoubleP
     * @Description //创建订单
     * @Date 13:05 2019/11/12
     * @Param [user, goodsVO]
     * @return com.avaj.ekill.model.OrderInfo
     **/
    @Override
    public OrderInfo createOrder(User user, GoodsVO goodsVO) {

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCrateTime(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsId(goodsVO.getId());
        BigDecimal bigDecimal = new BigDecimal(goodsVO.getSeckillPrice());
        orderInfo.setGoodsPrice(bigDecimal);
        orderInfo.setOrderChannel((byte)1);
        orderInfo.setStatus((byte)0);
        orderInfo.setUserId(user.getId());
        orderInfoMapper.insert(orderInfo);

        OrderKill orderKill = new OrderKill();
        orderKill.setOrderId(orderInfo.getGoodsId());
        orderKill.setUserId(1L);
        orderKill.setGoodsId(goodsVO.getId());
        orderInfoMapper.insertSeckillOrder(orderKill);

        redisService.set(OrderKey.getSeckillOrderByUidGid, "" + user.getId() + "_" +goodsVO.getId(), user);
        return orderInfo;
    }

    @Override
    public void deleteOrders() {
        orderInfoMapper.deleteOrders();
        orderInfoMapper.deleteSeckillOrders();
    }

    public OrderKill getSeckillOrderByUserIdGoodsId(Long userId, long goodsId) {

        return redisService.get(OrderKey.getSeckillOrderByUidGid, "" + userId + "_" + goodsId, OrderKill.class);
    }
}
