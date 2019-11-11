package com.avaj.ekill.mapper;

import com.avaj.ekill.model.Goods;
import com.avaj.ekill.vo.GoodsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    GoodsVO selectGoodsVOByPrimaryKey(Long goodsId);

    List<GoodsVO> selectAll();

    @Select("select g.*, sg.seckill_price, sg.stock_count, sg.start_time, sg.end_time from goods_kill sg left join " +
            "goods g on sg.goods_id=g.id")
    List<GoodsVO> listGoodsVO();
}