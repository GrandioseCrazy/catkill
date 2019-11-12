package com.avaj.ekill.service;

import com.avaj.ekill.model.OrderInfo;
import com.avaj.ekill.model.OrderKill;
import com.avaj.ekill.model.User;
import com.avaj.ekill.vo.GoodsVO;

public interface OrderService {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    OrderInfo createOrder(User user, GoodsVO goodsVO);

    void deleteOrders();

    public OrderKill getSeckillOrderByUserIdGoodsId(Long userId, long goodsId);
}
