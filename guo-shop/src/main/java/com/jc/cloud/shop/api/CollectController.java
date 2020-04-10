package com.jc.cloud.shop.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.entity.UserGoodsCollect;
import com.jc.cloud.shop.service.IUserGoodsCollectService;
import com.jc.cloud.vo.shop.GoodsVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 用户收藏表 前端控制器
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@RestController
@RequestMapping("/v1/collect")
public class CollectController {

    @Autowired
    IUserGoodsCollectService collectService;

    @ApiOperation(value = "查看用户的收藏商品")
    @GetMapping("/list")
    public ObjectRestResponse<List<GoodsVo>> list(){
        List<GoodsVo> goods = collectService.viewCollect();
        return new ObjectRestResponse<List<GoodsVo>>().ok(goods);
    }

    @ApiOperation(value = "添加收藏")
    @PostMapping("/save")
    public ObjectRestResponse<Object> save(String goodsId){
        return collectService.addCollect(goodsId);
    }

    @ApiOperation(value = "取消收藏")
    @GetMapping("/cancel")
    public ObjectRestResponse<Object> cancel(String goodsId){
        return collectService.cancel(goodsId);
    }
}

