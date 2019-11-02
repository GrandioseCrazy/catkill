package com.avaj.ekill.model;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Mapper
public class GoodsKill {
    private Long id;

    private Long goodsId;

    private BigDecimal seckillPrice;

    private Integer stockCount;

    private Date startTime;

    private Date endTime;

}