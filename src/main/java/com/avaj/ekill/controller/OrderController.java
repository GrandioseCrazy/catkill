package com.avaj.ekill.controller;

import com.avaj.ekill.model.OrderInfo;
import com.avaj.ekill.model.User;
import com.avaj.ekill.result.CodeMsg;
import com.avaj.ekill.result.Result;
import com.avaj.ekill.service.GoodsService;
import com.avaj.ekill.service.OrderService;
import com.avaj.ekill.vo.GoodsVO;
import com.avaj.ekill.vo.OrderDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("订单详情接口")
    @ApiImplicitParam()
    @GetMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVO> seckill(User user, @RequestParam("orderId") long orderId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        OrderInfo order = orderService.selectByPrimaryKey(orderId);

        if (order == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }

        long goodsId = order.getGoodsId();
        GoodsVO goodsVO = goodsService.selectGoodsVOByPrimaryKey(orderId);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setOrder(order);
        orderDetailVO.setGoods(goodsVO);

        return Result.success(orderDetailVO);
    }
}
