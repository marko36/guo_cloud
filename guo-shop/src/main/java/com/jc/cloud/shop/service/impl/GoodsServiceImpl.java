package com.jc.cloud.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.mapper.GoodsMapper;
import com.jc.cloud.shop.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.vo.shop.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author lgh
 * @since 2019-05-25
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    @Transactional
    public ObjectRestResponse<Goods> saveGoods(Goods goods) {
        try {
            ValidatorUtils.validateEntity(goods);
            goods.setCreateBy(BaseContextHandler.getUserName());
            boolean result = this.save(goods);
            if (!result){
                return new ObjectRestResponse<>().error("操作失败");
            }
            return new ObjectRestResponse<>().ok("添加成功");
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ObjectRestResponse<Goods> updateGoods(Goods goods) {
        try {
            ValidatorUtils.validateEntity(goods);
            goods.setUpdateBy(BaseContextHandler.getUserName());
            goods.setUpdateTime(new Date());
            boolean result = this.updateById(goods);
            if (!result){
                return new ObjectRestResponse<>().error("操作失败");
            }
            return new ObjectRestResponse<>().ok("更改成功");
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }
    }

    @Override
    public ObjectRestResponse<List<GoodsVo>> getPublishGoodsInfo() {
        List<GoodsVo> goodsVoList = goodsMapper.getPublishGoodsInfo();
        return new ObjectRestResponse<>().ok(goodsVoList);
    }


    @Override
    public ObjectRestResponse<GoodsVo> viewPage() {
        Page<Goods> page = new Page<>(1,5);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("goodsId");
        IPage<Goods> iPage = getBaseMapper().selectPage(page,queryWrapper);
        List<Goods> records= iPage.getRecords();
        return new ObjectRestResponse<>().ok(records);
    }

    @Override
    public ObjectRestResponse<List<GoodsVo>> getPublishGoodsByGoodsId(String goodsId) {
        List<GoodsVo> goodsVoList = goodsMapper.getPublishGoodsByGoodsId(goodsId);
        return new ObjectRestResponse<>().ok(goodsVoList);
    }

    @Override
    public GoodsVo getByGoodsIdAndGoodsAttribute(String goodsId, String goodsAttribute) {
        GoodsVo goodsVo = goodsMapper.getByGoodsIdAndGoodsAttribute(goodsId,goodsAttribute);
        return goodsVo;
    }


}
