package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Resource;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
public interface IResourceService extends IService<Resource> {

    ObjectRestResponse<Resource> getResourceById(Integer id);

    ObjectRestResponse<List<Resource>> getListByResource(Resource resource);

    ObjectRestResponse<Resource> saveResource(Resource resource);

    ObjectRestResponse<Resource> updateResource(Resource resource);

    ObjectRestResponse<Resource> editStatus(Resource resource);

    ObjectRestResponse<Resource> deleteResource(Integer id);

}
