package com.avaj.ekill.service.impl;

import com.avaj.ekill.mapper.GoodsKillMapper;
import com.avaj.ekill.model.GoodsKill;
import com.avaj.ekill.service.SkillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillGoodsServiceImpl implements SkillGoodsService {

    @Autowired
    GoodsKillMapper goodsKillMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return goodsKillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GoodsKill record) {
        return goodsKillMapper.insert(record);
    }

    @Override
    public int insertSelective(GoodsKill record) {
        return goodsKillMapper.insertSelective(record);
    }

    @Override
    public GoodsKill selectByPrimaryKey(Long id) {
        return goodsKillMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GoodsKill record) {
        return goodsKillMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GoodsKill record) {
        return goodsKillMapper.updateByPrimaryKey(record);
    }
}
