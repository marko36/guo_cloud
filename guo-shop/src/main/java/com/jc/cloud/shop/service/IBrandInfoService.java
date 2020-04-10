package com.jc.cloud.shop.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.BrandInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌详情表 服务类
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
public interface IBrandInfoService extends IService<BrandInfo> {

    ObjectRestResponse<BrandInfo> delete(Integer id);

    ObjectRestResponse<BrandInfo> addBrand(BrandInfo brandInfo);
}
