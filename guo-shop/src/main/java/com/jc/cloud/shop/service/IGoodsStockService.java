package com.jc.cloud.shop.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.entity.GoodsStock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品规格表 服务类
 * </p>
 *
 * @author lgh
 * @since 2019-05-25
 */
public interface IGoodsStockService extends IService<GoodsStock> {

    ObjectRestResponse<Object> getAllByGoodsId(String goodsId);

    ObjectRestResponse<Object> getByGoodsAttribute(String goodsId, String goodsAttribute);

    ObjectRestResponse<GoodsStock> saveStock(GoodsStock stock);
}
