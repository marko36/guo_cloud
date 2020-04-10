package com.jc.cloud.shop.mapper;

import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.entity.UserGoodsCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jc.cloud.vo.shop.GoodsVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户收藏表 Mapper 接口
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@Mapper
public interface UserGoodsCollectMapper extends BaseMapper<UserGoodsCollect> {

    @Select("select  g.* from ryg_goods g left join ryg_user_goods_collect c on g.goodsId=c.goodsId where c.userId= #{userId};")
    List<GoodsVo> viewCollect(@Param("userId") String userId);

}
