package com.avaj.ekill.service.impl;

import com.avaj.ekill.access.AccessLimit;
import com.avaj.ekill.mapper.GoodsKillMapper;
import com.avaj.ekill.mapper.GoodsMapper;
import com.avaj.ekill.model.Goods;
import com.avaj.ekill.model.GoodsKill;
import com.avaj.ekill.service.GoodsService;
import com.avaj.ekill.vo.GoodsVO;
import org.apache.ibatis.annotations.Select;
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
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Goods record) {
        return goodsMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

    @Override
    public GoodsVO selectGoodsVOByPrimaryKey(Long goodsId) {
        return goodsMapper.selectGoodsVOByPrimaryKey(goodsId);
    }

    @Override
    public List<GoodsVO> selectAll() {
        return goodsMapper.selectAll();
    }
    @Override
    public List<GoodsVO> listGoodsVO() {
        return goodsMapper.listGoodsVO();
    }

    @Override
    public boolean reduceStock(GoodsVO goods) {
        GoodsKill goodsKill = new GoodsKill();
        goodsKill.setId(goods.getId());
        int ret = goodsMapper.reduceStock(goodsKill);
        return ret > 0;
    }

    @Override
    public void resetStock(List<GoodsVO> goodsList) {
        for (GoodsVO goods : goodsList) {
            GoodsKill goodsKill = new GoodsKill();
            goodsKill.setId(goods.getId());
            goodsKill.setStockCount(goods.getStockCount());
            goodsMapper.resetStock(goodsKill);
        }
    }
}
