package com.avaj.ekill.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Goods {
    private Long id;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private BigDecimal goodsPrice;

    private Integer goodsStock;

    private String goodsDetail;
}