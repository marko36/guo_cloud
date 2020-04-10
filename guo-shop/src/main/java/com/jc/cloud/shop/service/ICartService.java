package com.jc.cloud.shop.service;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.vo.shop.CartVo;
import com.jc.cloud.vo.shop.GoodsVo;

import java.util.List;
import java.util.Map;


public interface ICartService {

    ObjectRestResponse<List<GoodsVo>> getCartList(String userId);

    ObjectRestResponse<Object> addCart(String userId, String goodsId, String goodsAttribute, Integer number);

    ObjectRestResponse<Object> updateCart(String userId, String goodsId, String goodsAttribute, Integer number);

    ObjectRestResponse<Object> delCartGoods(String userId, String goodsId, String goodsAttribute);

    ObjectRestResponse<Object> delCart(String userId);

    ObjectRestResponse<Object> check(String userId, String goodsId, String goodsAttribute, String check);

    ObjectRestResponse<Map<String ,Object>> toConfirm(String userId);
}
