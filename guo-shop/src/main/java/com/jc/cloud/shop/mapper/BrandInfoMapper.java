package com.jc.cloud.shop.mapper;

import com.jc.cloud.shop.entity.BrandInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 品牌详情表 Mapper 接口
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
@Mapper
public interface BrandInfoMapper extends BaseMapper<BrandInfo> {

}
