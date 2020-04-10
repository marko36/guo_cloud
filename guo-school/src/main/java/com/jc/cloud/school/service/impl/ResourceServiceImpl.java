package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Resource;
import com.jc.cloud.school.mapper.ResourceMapper;
import com.jc.cloud.school.service.IResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Override
    public ObjectRestResponse<Resource> getResourceById(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        Resource resource = this.getById(id);
        return resource == null ? response.error("不存在") : response.ok(resource);
    }

    @Override
    public ObjectRestResponse<List<Resource>> getListByResource(Resource resource) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("id,name.gradingId,themeId,cover,path,score,clickCount,collectCount");

        if(resource.getName() != null){//名称
            wrapper.like("name",resource.getName());
        }

        if(resource.getThemeId() != null){//主题
            wrapper.eq("themeId",resource.getThemeId());
        }

        if(resource.getGradingId() != null){//年龄段
            wrapper.eq("gradingId",resource.getGradingId());
        }

        return new ObjectRestResponse<Resource>().ok(this.list(wrapper));
    }

    @Override
    public ObjectRestResponse saveResource(Resource resource) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(resource) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updateResource(Resource resource) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(resource) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse editStatus(Resource resource) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(resource) ? response.ok("审核保存成功") : response.error("审核保存失败");
    }

    @Override
    public ObjectRestResponse deleteResource(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }
}
