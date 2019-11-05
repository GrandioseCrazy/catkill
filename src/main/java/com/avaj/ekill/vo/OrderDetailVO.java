package com.avaj.ekill.vo;

import com.avaj.ekill.model.OrderInfo;
import lombok.Data;

@Data
public class OrderDetailVO {
    private GoodsVO goods;
    private OrderInfo order;
}
