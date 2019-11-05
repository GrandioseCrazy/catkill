package com.avaj.ekill.service;

import com.avaj.ekill.model.GoodsKill;

public interface SkillGoodsService {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsKill record);

    int insertSelective(GoodsKill record);

    GoodsKill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsKill record);

    int updateByPrimaryKey(GoodsKill record);
}
