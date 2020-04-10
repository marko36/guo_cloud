package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.ResourceClassify;

import java.util.List;

/**
 * <p>
 * 资源分类 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
public interface IResourceClassifyService extends IService<ResourceClassify> {

    ObjectRestResponse<List<ResourceClassify>> getListByParentId(Integer parentId);

    ObjectRestResponse saveResourceClassify(ResourceClassify resourceClassify);

    ObjectRestResponse updateResourceClassify(ResourceClassify resourceClassify);

    ObjectRestResponse deleteResourceClassify(Integer id);

}
