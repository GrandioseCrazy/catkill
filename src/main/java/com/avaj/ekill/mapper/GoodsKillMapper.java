package com.avaj.ekill.mapper;

import com.avaj.ekill.model.GoodsKill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GoodsKillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsKill record);

    int insertSelective(GoodsKill record);

    GoodsKill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsKill record);

    int updateByPrimaryKey(GoodsKill record);


}