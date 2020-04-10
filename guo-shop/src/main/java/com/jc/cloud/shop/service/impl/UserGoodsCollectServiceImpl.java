package com.jc.cloud.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.entity.UserGoodsCollect;
import com.jc.cloud.shop.mapper.UserGoodsCollectMapper;
import com.jc.cloud.shop.service.IUserGoodsCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.vo.shop.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户收藏表 服务实现类
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@Service
public class UserGoodsCollectServiceImpl extends ServiceImpl<UserGoodsCollectMapper, UserGoodsCollect> implements IUserGoodsCollectService {

    @Autowired
    UserGoodsCollectMapper mapper;

    @Override
    public List<GoodsVo> viewCollect() {
        return mapper.viewCollect(BaseContextHandler.getUserId());
    }

    @Override
    @Transactional
    public ObjectRestResponse addCollect(String goodsId) {
        if (goodsId == null){
            return new ObjectRestResponse().error("商品id不能为空");
        }
        UserGoodsCollect collect = new UserGoodsCollect();
        collect.setUserId(BaseContextHandler.getUserId());
        collect.setGoodsId(goodsId);
        boolean result = this.save(collect);
        if (!result){
            return new ObjectRestResponse().error("操作失败");
        }
        return new ObjectRestResponse().ok("添加成功");
    }

    @Override
    @Transactional
    public ObjectRestResponse<Object> cancel(String goodsId) {
        if (goodsId == null){
            return new ObjectRestResponse<>().error("商品id不能为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("userId",BaseContextHandler.getUserId());
        queryWrapper.eq("goodsId",goodsId);
        boolean result = remove(queryWrapper);
        if (!result){
            return new ObjectRestResponse<>().error("操作失败");
        }
        return new ObjectRestResponse<>().ok("取消成功");
    }
}
