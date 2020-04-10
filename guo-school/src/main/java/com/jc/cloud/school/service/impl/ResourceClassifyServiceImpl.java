package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.ResourceClassify;
import com.jc.cloud.school.mapper.ResourceClassifyMapper;
import com.jc.cloud.school.service.IResourceClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源分类 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Service
public class ResourceClassifyServiceImpl extends ServiceImpl<ResourceClassifyMapper, ResourceClassify> implements IResourceClassifyService {

    @Override
    public ObjectRestResponse<List<ResourceClassify>> getListByParentId(Integer parentId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("id,name,parentId")
                .eq("",parentId);
        wrapper.or();
        wrapper.eq("id",parentId);
        return new ObjectRestResponse<List<ResourceClassify>>().ok(this.list(wrapper));
    }

    @Override
    public ObjectRestResponse saveResourceClassify(ResourceClassify resourceClassify) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(resourceClassify) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updateResourceClassify(ResourceClassify resourceClassify) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(resourceClassify) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse deleteResourceClassify(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }
}
