package com.avaj.ekill.service.impl;

import com.avaj.ekill.model.*;
import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.redis.SeckillKey;
import com.avaj.ekill.service.GoodsService;
import com.avaj.ekill.service.OrderService;
import com.avaj.ekill.util.MD5Util;
import com.avaj.ekill.util.UUIDUtil;
import com.avaj.ekill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Service
public class SeckillService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;

    @Transactional
    public OrderInfo seckill(User user, GoodsVO goodsVO) {
        // 减库存 下订单
        boolean success = goodsService.reduceStock(goodsVO);

        if (success) {
            return orderService.createOrder(user,goodsVO);
        }
        setGoodsOver(goodsVO.getId());
        return null;
    }

    public long getSeckillResult(Long userId, long goodsId) {
        OrderKill orderKill = orderService.getSeckillOrderByUserIdGoodsId(userId,goodsId);

        if (orderKill != null) {
            // 秒杀成功
            return orderKill.getOrderId();
        } else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;
            } else {
                return 0;
            }

        }
    }

    // 验证路径
    public boolean checkPath(User seckillUser, long goodsId, String path) {
        if (seckillUser == null || path == null) {
            return false;
        }
        String pathOld = redisService.get(SeckillKey.getSeckillPath, "" + seckillUser.getId() + "_" + goodsId, String.class);
        return path.equals(pathOld);
    }

    // 创建路径
    public String createSeckillPath(User seckillUser, long goodsId) {

        String str = MD5Util.md5(UUIDUtil.uuid() + "123456");
        redisService.set(SeckillKey.getSeckillPath, "" + seckillUser.getId() + "_" + goodsId, str);
        return str;
    }


    public BufferedImage createVerifyCode(User seckillUser, long goodsId) {
        if (seckillUser == null || goodsId < 0) {
            return null;
        }
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 24));
        g.drawString(verifyCode + "=", 8, 24);
        g.dispose();
        //把验证码存到redis中
        int rnd = calc(verifyCode);
        redisService.set(SeckillKey.getSeckillVerifyCode, seckillUser.getId() + "," + goodsId, rnd);
        //输出图片
        return image;
    }

    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(SeckillKey.isGoodsOver, "" + goodsId);
    }


    //商品已经卖完了
    private void setGoodsOver(Long id) {
        redisService.set(SeckillKey.isGoodsOver,"" + id, true);
    }
    private static int calc(String exp) {
        try {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            return (Integer) engine.eval(exp);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    private static char[] ops = new char[]{'+', '-', '*'};
    private String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = ops[rdm.nextInt(3)];
        char op2 = ops[rdm.nextInt(3)];
        String exp = "" + num1 + op1 + num2 + op2 + num3;
        System.out.println(exp);
        return exp;
    }

}
