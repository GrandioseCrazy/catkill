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
        return 0;
    }

    @Override
    public int insert(GoodsKill record) {
        return 0;
    }

    @Override
    public int insertSelective(GoodsKill record) {
        return 0;
    }

    @Override
    public GoodsKill selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsKill record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(GoodsKill record) {
        return 0;
    }
}
