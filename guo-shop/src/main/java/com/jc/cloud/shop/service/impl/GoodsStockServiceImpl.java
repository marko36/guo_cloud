package com.jc.cloud.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.entity.GoodsStock;
import com.jc.cloud.shop.mapper.GoodsStockMapper;
import com.jc.cloud.shop.service.IGoodsStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationEvent;

/**
 * <p>
 * 商品规格表 服务实现类
 * </p>
 *
 * @author lgh
 * @since 2019-05-25
 */
@Service
public class GoodsStockServiceImpl extends ServiceImpl<GoodsStockMapper, GoodsStock> implements IGoodsStockService {

    @Autowired
    GoodsStockMapper mapper;

    @Override
    public ObjectRestResponse<Object> getAllByGoodsId(String goodsId) {
        if (goodsId == null){
            return new ObjectRestResponse<>().error("商品Id不能为空");
        }
        Integer stock = mapper.getAllByGoodsId(goodsId);
          if (stock > 0){
             return new ObjectRestResponse<>().data(stock);
        }else {
             return new ObjectRestResponse<>().error("库存不足");
         }
    }

    @Override
    public ObjectRestResponse<Object> getByGoodsAttribute(String goodsId, String goodsAttribute) {
        if (goodsId == null || goodsAttribute == null){
            return new ObjectRestResponse<>().error("商品id和规格不能为空");
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("goodsId",goodsId);
        wrapper.eq("goodsAttribute",goodsAttribute);
        GoodsStock goodsStock = this.getOne(wrapper);
        if (goodsStock.getStock() == null){
            return new ObjectRestResponse<>().error("没有该商品或没有该规格");
        }else if (goodsStock.getStock() == 0){
            return new ObjectRestResponse<>().error("库存不足");
        }else {
            return new ObjectRestResponse<>().data(goodsStock.getStock());
        }
    }

    @Override
    @Transactional
    public ObjectRestResponse<GoodsStock> saveStock(GoodsStock stock) {
        try {
            ValidatorUtils.validateEntity(stock);
            boolean result = this.save(stock);
            if (!result){
                return new ObjectRestResponse<>().error("操作失败");
            }
            return new ObjectRestResponse<>().ok("更新成功");
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }
    }


}
