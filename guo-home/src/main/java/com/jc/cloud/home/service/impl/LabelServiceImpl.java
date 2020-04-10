package com.jc.cloud.home.service.impl;

import com.jc.cloud.home.entity.Label;
import com.jc.cloud.home.mapper.LabelMapper;
import com.jc.cloud.home.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author liuqing
 * @since 2019-05-24
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

}
