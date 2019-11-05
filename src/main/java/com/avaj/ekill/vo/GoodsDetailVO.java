package com.avaj.ekill.vo;

import com.avaj.ekill.model.User;
import lombok.Data;

@Data
public class GoodsDetailVO {

    private int seckillStatus = 0;
    private int remainSeconds = 0;
    private GoodsVO goods;
    private User user;
}
