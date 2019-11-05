package com.avaj.ekill.service.impl;

import com.avaj.ekill.mapper.OrderInfoMapper;
import com.avaj.ekill.model.OrderInfo;
import com.avaj.ekill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(OrderInfo record) {
        return 0;
    }

    @Override
    public int insertSelective(OrderInfo record) {
        return 0;
    }

    @Override
    public OrderInfo selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(OrderInfo record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(OrderInfo record) {
        return 0;
    }
}
