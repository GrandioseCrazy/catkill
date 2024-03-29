package com.avaj.ekill.mapper;

import com.avaj.ekill.model.OrderKill;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderKillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderKill record);

    int insertSelective(OrderKill record);

    OrderKill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderKill record);

    int updateByPrimaryKey(OrderKill record);
}