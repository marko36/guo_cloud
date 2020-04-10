package com.jc.cloud.home.service;

import com.jc.cloud.home.entity.VipService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 支付 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-09
 */
public interface IVipServiceService extends IService<VipService> {

    List<VipService> getVipType(Integer type);
}
