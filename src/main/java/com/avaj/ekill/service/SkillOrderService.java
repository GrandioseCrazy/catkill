package com.avaj.ekill.service;

import com.avaj.ekill.model.OrderKill;

public interface SkillOrderService {
    int deleteByPrimaryKey(Long id);

    int insert(OrderKill record);

    int insertSelective(OrderKill record);

    OrderKill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderKill record);

    int updateByPrimaryKey(OrderKill record);
}
