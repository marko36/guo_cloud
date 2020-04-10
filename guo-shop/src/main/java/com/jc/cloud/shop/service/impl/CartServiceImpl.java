package com.jc.cloud.shop.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.redis.shop.CartPrefix;
import com.jc.cloud.common.util.RedisUtil;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.entity.GoodsStock;
import com.jc.cloud.shop.feign.AddressFeign;
import com.jc.cloud.shop.service.ICartService;
import com.jc.cloud.shop.service.IGoodsService;
import com.jc.cloud.shop.service.IGoodsStockService;
import com.jc.cloud.vo.shop.CartVo;
import com.jc.cloud.vo.shop.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AddressFeign addressFeign;

    @Autowired
    IGoodsService goodsService;

    @Autowired
    IGoodsStockService stockService;

    @Override
    public ObjectRestResponse<List<GoodsVo>> getCartList(String userId) {
        JSONArray jsonList = redisUtil.hgetList(CartPrefix.getCartList,userId);
        if (jsonList == null){
            return new ObjectRestResponse<>().ok();
        }
        List<CartVo> list = JSONObject.parseArray(jsonList.toJSONString(),CartVo.class);
        List<GoodsVo> cartVoList = new ArrayList<>();
        for (CartVo cartVo : list){
           GoodsVo goodsVo =  goodsService.getByGoodsIdAndGoodsAttribute(cartVo.getGoodsId(),cartVo.getGoodsAttribute());
           goodsVo.setCheck(cartVo.getCheck());
            cartVoList.add(goodsVo);
        }
        return new ObjectRestResponse<>().ok(cartVoList);
    }

    @Override
    public ObjectRestResponse<Object> addCart(String userId, String goodsId, String goodsAttribute, Integer number) {
        Boolean exists = redisUtil.hHasKey(CartPrefix.getCartList,userId,goodsId,goodsAttribute);
        if (exists){
            CartVo cartVo= redisUtil.hget(CartPrefix.getCartList,userId,goodsId,goodsAttribute,CartVo.class);
            cartVo.setNumber(cartVo.getNumber()+number);
            redisUtil.hset(CartPrefix.getCartList,userId,goodsId,goodsAttribute,cartVo);
            return new ObjectRestResponse<>().ok("操作成功");
        }
        Goods goods = goodsService.getById(goodsId);
        if (goods == null){
            return new ObjectRestResponse<>().error("操作失败");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("goodsId",goodsId);
        queryWrapper.eq("goodsAttribute",goodsAttribute);
        GoodsStock goodsStock = stockService.getOne(queryWrapper);
        if (goodsStock == null){
            return new ObjectRestResponse<>().error("操作失败");
        }
        CartVo cartVo = new CartVo();
        cartVo.setGoodsId(goodsId);
        cartVo.setGoodsAttribute(goodsAttribute);
        cartVo.setNumber(number);
        cartVo.setCheck("false");
        redisUtil.hset(CartPrefix.getCartList,userId,goodsId,goodsAttribute,cartVo);
        return new ObjectRestResponse<>().ok("操作成功");
    }

    @Override
    public ObjectRestResponse<Object> updateCart(String userId, String goodsId, String goodsAttribute, Integer number) {
        CartVo cartVo = redisUtil.hget(CartPrefix.getCartList,userId,goodsId,goodsAttribute,CartVo.class);
        if (cartVo == null){
            return new ObjectRestResponse<>().error("购物车中没有该商品");
        }
        cartVo.setNumber(cartVo.getNumber()+number);
        if (cartVo.getNumber() == 0){
           redisUtil.hdel(CartPrefix.getCartList,userId,goodsId,goodsAttribute);
           return new ObjectRestResponse<>().ok();
        }
        boolean result = redisUtil.hset(CartPrefix.getCartList,userId,goodsId,goodsAttribute,cartVo);
        if (!result){
            return new ObjectRestResponse<>().error("操作失败");
        }
        return new ObjectRestResponse<>().ok("操作成功");
    }

    @Override
    public ObjectRestResponse<Object> delCartGoods(String userId, String goodsId, String goodsAttribute) {
        boolean result = redisUtil.hdel(CartPrefix.getCartList,userId,goodsId,goodsAttribute);
        if (!result){
            return new ObjectRestResponse<>().error("操作失败");
        }
        return new ObjectRestResponse<>().ok("删除成功");
    }

    @Override
    public ObjectRestResponse<Object> delCart(String userId) {
        redisUtil.del(CartPrefix.getCartList,userId);
        return new ObjectRestResponse<>().ok("删除成功");
    }

    @Override
    public ObjectRestResponse<Object> check(String userId, String goodsId, String goodsAttribute, String check) {
        CartVo cartVo = redisUtil.hget(CartPrefix.getCartList,userId,goodsId,goodsAttribute,CartVo.class);
        String flag = "true" .equals(check) ? "true":"false";
        cartVo.setCheck(flag);
        boolean result = redisUtil.hset(CartPrefix.getCartList,userId,goodsId,goodsAttribute,cartVo);
        if (!result){
            return new ObjectRestResponse<>().error();
        }
        return new ObjectRestResponse<>().ok();
    }

    @Override
    public ObjectRestResponse<Map<String ,Object>> toConfirm(String userId) {
        ObjectRestResponse<List<GoodsVo>> restResponse = getCartList(userId);
        if (!restResponse.isSuccess()){
            return new ObjectRestResponse<>().error(restResponse.getMessage());
        }
        Map<String ,Object> map = new HashMap<>();
        for (GoodsVo goodsVo:restResponse.getData()){
            if ("true".equals(goodsVo.getCheck())){
                map.put("item"+goodsVo.getGoodsId(),goodsVo);
            }
        }
        ObjectRestResponse<Object> res = addressFeign.getDefalutAddress();
        if(!res.isSuccess()){
            return new ObjectRestResponse<>().error(res.getMessage());
        }
        map.put("address",res.getData());
        return new ObjectRestResponse<>().ok(map);
    }


}
