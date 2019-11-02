package com.avaj.ekill.model;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.Date;
@Data
@Mapper
public class OrderInfo {
    private Long id;

    private Long userId;

    private Long goodsId;

    private Long deliveryAddrId;

    private String goodsName;

    private Integer goodsCount;

    private BigDecimal goodsPrice;

    private Byte orderChannel;

    private Byte status;

    private Date crateTime;

    private Date payTime;

}