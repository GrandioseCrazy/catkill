package com.avaj.ekill.vo;

import com.avaj.ekill.model.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class GoodsVO extends Goods {
    private Double seckillPrice;
    private Integer stockCount;
    private Date startTime;
    private Date endTime;
}
