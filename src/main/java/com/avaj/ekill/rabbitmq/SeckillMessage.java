package com.avaj.ekill.rabbitmq;

import com.avaj.ekill.model.User;
import com.avaj.ekill.redis.SeckillUSerKey;
import lombok.Data;

@Data
public class SeckillMessage {

    private User user;

    private long goodsId;

    @Override
    public String toString() {
        return "SeckillMessage{" +
                "user=" + user +
                ", goodsId=" + goodsId +
                '}';
    }
}
