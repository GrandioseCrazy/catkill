package com.avaj.ekill.controller;

import com.avaj.ekill.access.AccessLimit;
import com.avaj.ekill.model.User;
import com.avaj.ekill.rabbitmq.MQSender;
import com.avaj.ekill.redis.GoodsKey;
import com.avaj.ekill.redis.OrderKey;
import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.redis.SeckillKey;
import com.avaj.ekill.result.Result;
import com.avaj.ekill.service.GoodsService;
import com.avaj.ekill.service.OrderService;
import com.avaj.ekill.service.impl.SeckillService;
import com.avaj.ekill.vo.GoodsVO;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MQSender sender;
    // 这是干啥
    private Map<Long,Boolean> localOverMap = new HashMap<>();

    // 系统初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        if (goodsVOList == null) {
            return;
        }
        for (GoodsVO goodsVO : goodsVOList) {
            redisService.set(GoodsKey.getSeckillGoodsStock,""+goodsVO.getId(),goodsVO.getStockCount());
            localOverMap.put(goodsVO.getId(),false);
        }
    }
    @ApiOperation("db 数据重置接口")
    @GetMapping("/reset")
    @ResponseBody
    public Result<Boolean> reset() {
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();

        for (GoodsVO goodsVO : goodsVOList) {
            goodsVO.setStockCount(10);
            redisService.set(GoodsKey.getSeckillGoodsStock,"" + goodsVO.getId(),10);
            localOverMap.put(goodsVO.getId(),false);
        }
        redisService.delete(OrderKey.getSeckillOrderByUidGid);
        redisService.delete(SeckillKey.isGoodsOver);
        return Result.success(true);
    }

    @ApiOperation("秒杀接口")
    @PostMapping("/{path}/seckill")
    @ResponseBody
    @AccessLimit(seconds = 5, maxCount = 5)
    public Result<Integer> seckill(User user, @RequestParam("goodsId") long goodsId,
                                   @PathVariable("path") String path) {

        // 验证path
//        boolean check = seckillService.
    return  null;
    }
}
