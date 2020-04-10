package com.jc.cloud.home.service.impl;

import com.jc.cloud.home.entity.WorkDetails;
import com.jc.cloud.home.mapper.WorkDetailsMapper;
import com.jc.cloud.home.service.IWorkDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行驶记录 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-05
 */
@Service
public class WorkDetailsServiceImpl extends ServiceImpl<WorkDetailsMapper, WorkDetails> implements IWorkDetailsService {

}
