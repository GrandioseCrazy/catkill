package com.avaj.ekill.service.impl;

import com.avaj.ekill.mapper.OrderKillMapper;
import com.avaj.ekill.model.GoodsKill;
import com.avaj.ekill.service.SkillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillOrderServiceImpl implements SkillGoodsService {

    @Autowired
    OrderKillMapper orderKillMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderKillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GoodsKill record) {
        return orderKillMapper.insert(record);
    }

    @Override
    public int insertSelective(GoodsKill record) {
        return orderKillMapper.insertSelective(record);
    }

    @Override
    public GoodsKill selectByPrimaryKey(Long id) {
        return orderKillMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsKill record) {
        return orderKillMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GoodsKill record) {
        return orderKillMapper.updateByPrimaryKey(record);
    }
}
