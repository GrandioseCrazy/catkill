package com.avaj.ekill.controller;

import com.avaj.ekill.model.Goods;
import com.avaj.ekill.model.User;
import com.avaj.ekill.redis.GoodsKey;
import com.avaj.ekill.redis.RedisService;
import com.avaj.ekill.result.Result;
import com.avaj.ekill.service.GoodsService;
import com.avaj.ekill.vo.GoodsDetailVO;
import com.avaj.ekill.vo.GoodsVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    /*
    获取商品列表
    加入缓存
     */
    @RequestMapping("/getList")
    @ApiOperation("获取商品列表")
    @ApiImplicitParam(name = "user",value = "用户实体",required = true,dataType ="User" )
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
        // 取缓存
        String html = redisService.get(GoodsKey.getGoodsList,"",String.class);
        if (!StringUtils.isEmpty(html))
            return html;

        model.addAttribute("user",user);
        List<GoodsVO> goodsVOList = goodsService.selectAll();
        model.addAttribute("goodsList",goodsVOList);

        SpringWebContext ctx = new SpringWebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);

        html = thymeleafViewResolver.getTemplateEngine().process("goodsList",ctx);

        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsList,"",html);
        }
        return html;
    }

    @ApiOperation("单个商品详情 返回json")
    @ApiImplicitParam(name = "goodsId",value = "商品ID",required = true,paramType = "path")
    @GetMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVO> detail(User user, @PathVariable("goodsId")long goodsId) {
        GoodsVO goodsVO = goodsService.selectGoodsVOByPrimaryKey(goodsId);
        long startAt = goodsVO.getStartTime().getTime();
        long endAt = goodsVO.getEndTime().getTime();

        Map<String,Integer> map = checkTime(startAt,endAt);

        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        goodsDetailVO.setGoods(goodsVO);
        goodsDetailVO.setUser(user);
        goodsDetailVO.setRemainSeconds(map.get("remainSeconds"));
        goodsDetailVO.setSeckillStatus(map.get("seckillStatus"));

        return Result.success(goodsDetailVO);
    }

    private Map<String, Integer> checkTime(long startAt, long endAt) {
        Map<String, Integer> map = new HashMap<>();

        long now = System.currentTimeMillis();
        int seckillStatus = 0;
        int remainSeconds = 0;

        if (now < startAt) {
            //秒杀还没开始，倒计时
            seckillStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {
            //秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        } else {
            //秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }
        map.put("seckillStatus", seckillStatus);
        map.put("remainSeconds", remainSeconds);
        return map;

    }

    @ApiOperation("单个商品详情 返回HTML")
    @GetMapping(value = "/to_detail/{goodsId}", produces = "text/html")
    @ResponseBody
    public String detail(HttpServletRequest request, HttpServletResponse response, Model model, User user,
                        @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user",user);

        // 取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, "" + goodsId, String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }

        GoodsVO goodsVO = goodsService.selectGoodsVOByPrimaryKey(goodsId);
        model.addAttribute("goods",goodsVO);

        long startAt = goodsVO.getStartTime().getTime();
        long endAt = goodsVO.getEndTime().getTime();

        Map<String ,Integer> map = checkTime(startAt,endAt);

        model.addAttribute("seckillStatus", map.get("seckillStatus"));
        model.addAttribute("remainSeconds", map.get("remainSeconds"));

        SpringWebContext ctx = new SpringWebContext(request, response, request.getServletContext(),
                request.getLocale(), model.asMap(), applicationContext);
        // 手动渲染html
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail",ctx);

        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsDetail, "" + goodsId, html);
        }
        return html;
    }
}
