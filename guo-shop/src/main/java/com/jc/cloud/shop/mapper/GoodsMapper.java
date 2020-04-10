package com.jc.cloud.shop.mapper;

import com.jc.cloud.shop.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.cloud.vo.shop.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("select g.goodsId,g.cid,g.name,g.searchIcon,g.goodsNo,g.img,s.goodsAttribute,s.price,s.stock," +
            "g.contents,g.keywords,g.description,g.pv,g.auditStatus,g.brandId from ryg_goods g " +
            "left join ryg_goods_stock s on g.goodsId = s.goodsId where g.publishStatus = 1" +
            " group by g.goodsId having MIN(s.price)")
    List<GoodsVo> getPublishGoodsInfo();

    @Select("select g.goodsId,g.cid,g.name,g.searchIcon,g.goodsNo,g.img,s.goodsAttribute,s.price,s.stock," +
            "g.contents,g.keywords,g.description,g.pv,g.auditStatus,g.brandId from ryg_goods g " +
            "left join ryg_goods_stock s on g.goodsId = s.goodsId where g.publishStatus = 1 and g.goodsId = #{goodsId}")
    List<GoodsVo> getPublishGoodsByGoodsId(@Param("goodsId") String goodsId);

    @Select("select g.goodsId,g.cid,g.name,g.searchIcon,g.goodsNo,g.img,s.goodsAttribute,s.price,s.stock," +
            "g.contents,g.keywords,g.description,g.pv,g.auditStatus,g.brandId from ryg_goods g " +
            "left join ryg_goods_stock s on g.goodsId = s.goodsId where g.publishStatus = 1 and g.goodsId = #{goodsId} and s.goodsAttribute = #{goodsAttribute}")
    GoodsVo getByGoodsIdAndGoodsAttribute(@Param("goodsId") String goodsId, @Param("goodsAttribute") String goodsAttribute);
}
