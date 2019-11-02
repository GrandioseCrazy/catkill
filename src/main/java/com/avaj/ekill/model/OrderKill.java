package com.avaj.ekill.model;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

@Data
@Mapper
public class OrderKill {
    private Long id;

    private Long userId;

    private Long goodsId;

    private Long orderId;

}