package com.avaj.ekill.service.impl;

import com.avaj.ekill.mapper.GoodsMapper;
import com.avaj.ekill.model.Goods;
import com.avaj.ekill.service.GoodsService;
import com.avaj.ekill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods record) {
        return 0;
    }

    @Override
    public int insertSelective(Goods record) {
        return 0;
    }

    @Override
    public Goods selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Goods record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return 0;
    }

    @Override
    public GoodsVO selectGoodsVOByPrimaryKey(Long goodsId) {
        return null;
    }

    @Override
    public List<GoodsVO> selectAll() {
        return null;
    }
}
