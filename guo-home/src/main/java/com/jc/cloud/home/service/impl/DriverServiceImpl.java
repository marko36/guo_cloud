package com.jc.cloud.home.service.impl;

import com.jc.cloud.home.entity.Driver;
import com.jc.cloud.home.mapper.DriverMapper;
import com.jc.cloud.home.service.IDriverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 司机 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-09
 */
@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements IDriverService {

}
