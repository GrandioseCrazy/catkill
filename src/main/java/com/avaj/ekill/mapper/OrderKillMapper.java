package com.avaj.ekill.mapper;

import com.avaj.ekill.model.OrderKill;

public interface OrderKillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderKill record);

    int insertSelective(OrderKill record);

    OrderKill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderKill record);

    int updateByPrimaryKey(OrderKill record);
}