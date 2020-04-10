package com.jc.cloud.basic.service.impl;

import com.jc.cloud.basic.entity.Broadcast;
import com.jc.cloud.basic.mapper.BroadcastMapper;
import com.jc.cloud.basic.service.IBroadcastService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 * 如意家园轮播图 服务实现类
 * </p>
 *
 * @author lgh
 * @since 2019-05-31
 */
@Service
public class BroadcastServiceImpl extends ServiceImpl<BroadcastMapper, Broadcast> implements IBroadcastService {

    @Override
    public ObjectRestResponse<Object> saveBroadcast(Broadcast broadcast) {
        try {
            save(broadcast);
            return new ObjectRestResponse<>().ok();
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }

    }

    @Override
    public ObjectRestResponse<Object> updByBroadcast(Broadcast broadcast) {
        try {
            updateById(broadcast);
            return new ObjectRestResponse<>().ok();
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }
    }

    @Override
    public void delByIds(String ids) {
        if (ids.indexOf(",") < 0){
            this.removeById(ids);
        }else {
            this.removeByIds(Arrays.asList(ids.split(",")));
        }
    }
}
