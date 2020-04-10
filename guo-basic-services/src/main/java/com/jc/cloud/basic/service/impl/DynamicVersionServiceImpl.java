package com.jc.cloud.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.basic.entity.DynamicVersion;
import com.jc.cloud.basic.enums.DynamicTpey;
import com.jc.cloud.basic.mapper.DynamicVersionMapper;
import com.jc.cloud.basic.service.IDynamicVersionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fangliai
 * @since 2019-05-22
 */
@Service
@Log4j2
public class DynamicVersionServiceImpl extends ServiceImpl<DynamicVersionMapper, DynamicVersion> implements IDynamicVersionService {

    @Override
    public void updateVersion(DynamicTpey dynamicTpey)throws Exception {
       DynamicVersion dynamicVersion=getDynamVersionByPath(dynamicTpey.name());
       dynamicVersion.setVersion(dynamicVersion.getVersion()+1);
       updateById(dynamicVersion);
    }

    @Override
    public Long getLastVersion(DynamicTpey dynamicTpey)throws Exception {
        DynamicVersion dynamicVersion=getDynamVersionByPath(dynamicTpey.name());
        return dynamicVersion.getVersion();
    }

    private DynamicVersion getDynamVersionByPath(String key)throws Exception{
        DynamicVersion dynamicVersion=null;
        try {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("path",key);
            dynamicVersion=getOne(queryWrapper);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("db error");
        }
        if(dynamicVersion==null){
            throw new Exception("not find version for path【"+key+"】");
        }
        return  dynamicVersion;
    }
}
