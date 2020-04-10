package com.jc.cloud.shop.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.vo.shop.GoodsVo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author lgh
 * @since 2019-05-25
 */
public interface IGoodsService extends IService<Goods> {


    ObjectRestResponse<Goods> saveGoods(Goods goods);

    ObjectRestResponse<Goods> updateGoods(Goods goods);

    ObjectRestResponse<List<GoodsVo>> getPublishGoodsInfo();

    ObjectRestResponse<GoodsVo> viewPage();

    ObjectRestResponse<List<GoodsVo>> getPublishGoodsByGoodsId(String goodsId);

    GoodsVo getByGoodsIdAndGoodsAttribute(String goodsId, String goodsAttribute);
}
