package com.jc.cloud.shop.mapper;

import com.jc.cloud.shop.entity.Catagory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商品分类表 Mapper 接口
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
@Mapper
public interface CatagoryMapper extends BaseMapper<Catagory> {

}
