package com.jc.cloud.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.shop.entity.BrandInfo;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.mapper.BrandInfoMapper;
import com.jc.cloud.shop.service.IBrandInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.shop.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 品牌详情表 服务实现类
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
@Service
public class BrandInfoServiceImpl extends ServiceImpl<BrandInfoMapper, BrandInfo> implements IBrandInfoService {

    @Autowired
    IGoodsService goodsService;


    @Override
    @Transactional
    public ObjectRestResponse<BrandInfo> delete(Integer Id) {
        if (Id == null){
            return new ObjectRestResponse<>().error("品牌Id不能为空");
        }
        try {
            //根据brandId查找商品表中的商品
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("brandId",Id);
            List<Goods> goods = goodsService.list(queryWrapper);
            //根据brandId查找出来的list判断是否有商品在上架
            if (goods != null && goods.size()>0) {
                for (Goods goods1 : goods) {
                    Integer goodsId = (Integer) JSON.parse(goods1.getGoodsId());
                    if (goods.get(goodsId - 1).getPublishStatus() == 1) {  //上架状态码为1
                        return new ObjectRestResponse<>().error("不能删除还在上架的商品品牌");
                    }
                }
            }
            boolean result = removeById(Id);
            if (!result){
                return new ObjectRestResponse<>().error("操作失败");
            }
            return new ObjectRestResponse<>().ok("删除成功");
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }


    }

    @Override
    @Transactional
    public ObjectRestResponse<BrandInfo> addBrand(BrandInfo brandInfo) {
        try {
            ValidatorUtils.validateEntity(brandInfo);
           boolean result =  this.save(brandInfo);
           if (!result){
            return new ObjectRestResponse<>().error("操作失败");
           }
           return new ObjectRestResponse<>().ok("添加成功");
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }
    }
}
