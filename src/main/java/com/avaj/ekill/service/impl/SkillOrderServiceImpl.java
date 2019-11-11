package com.avaj.ekill.service.impl;

import com.avaj.ekill.mapper.OrderKillMapper;
import com.avaj.ekill.model.OrderKill;
import com.avaj.ekill.service.SkillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillOrderServiceImpl implements SkillOrderService {

    @Autowired
    OrderKillMapper orderKillMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderKillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderKill record) {
        return orderKillMapper.insert(record);
    }

    @Override
    public int insertSelective(OrderKill record) {
        return orderKillMapper.insertSelective(record);
    }

    @Override
    public OrderKill selectByPrimaryKey(Long id) {
        return orderKillMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderKill record) {
        return orderKillMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(OrderKill record) {
        return orderKillMapper.updateByPrimaryKey(record);
    }
}
