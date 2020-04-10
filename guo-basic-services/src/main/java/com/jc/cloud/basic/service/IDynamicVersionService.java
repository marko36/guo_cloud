package com.jc.cloud.basic.service;

import com.jc.cloud.basic.entity.DynamicVersion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.basic.enums.DynamicTpey;
import sun.rmi.runtime.Log;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fangliai
 * @since 2019-05-22
 */
public interface IDynamicVersionService extends IService<DynamicVersion> {
    /**
     * 更新配制版本信息
     * @param key
     */
    void  updateVersion(DynamicTpey dynamicTpey)throws Exception;

    /**
     * 获取最新配制版本
     * @param key
     * @return
     */
    Long getLastVersion(DynamicTpey dynamicTpey)throws Exception;


}
