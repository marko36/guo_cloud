package com.jc.cloud.shop.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.entity.UserGoodsCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.vo.shop.GoodsVo;

import java.util.List;

/**
 * <p>
 * 用户收藏表 服务类
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
public interface IUserGoodsCollectService extends IService<UserGoodsCollect> {

    List<GoodsVo> viewCollect();

    ObjectRestResponse addCollect(String goodsId);

    ObjectRestResponse<Object> cancel(String goodsId);
}
