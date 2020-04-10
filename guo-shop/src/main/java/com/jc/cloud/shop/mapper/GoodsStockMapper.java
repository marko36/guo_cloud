package com.jc.cloud.shop.mapper;

import com.jc.cloud.shop.entity.GoodsStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 商品规格表 Mapper 接口
 * </p>
 *
 * @author lgh
 * @since 2019-05-25
 */
@Mapper
public interface GoodsStockMapper extends BaseMapper<GoodsStock> {

    @Select("select sum(stock) from ryg_goods_stock where goodsId = #{goodsId}")
    Integer getAllByGoodsId(@Param("goodsId") String goodsId);
}
