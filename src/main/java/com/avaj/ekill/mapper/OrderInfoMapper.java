package com.avaj.ekill.mapper;

import com.avaj.ekill.model.OrderInfo;
import com.avaj.ekill.model.OrderKill;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    @Insert("insert into order_kill(user_id,goods_id,order_id) values(#{userId},#{goodsId},#{orderId})")
    void insertSeckillOrder(OrderKill orderKill);

    @Delete("delete from order_info")
    public void deleteOrders();

    @Delete("delete from order_kill")
    public void deleteSeckillOrders();
}