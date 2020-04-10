package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.ResourceClassify;
import com.jc.cloud.school.service.IResourceClassifyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * <p>
 * 资源分类 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/resourceClassify")
public class ResourceClassifyController {

    @Autowired
    IResourceClassifyService classifyService;

    @ApiOperation("根据id或上级id获取分类集合")
    @GetMapping("/getList")
    public ObjectRestResponse getList(Integer parentId){
        return classifyService.getListByParentId(parentId);
    }

    @ApiOperation("增加/保存分类")
    @PostMapping("/save")
    public ObjectRestResponse save(ResourceClassify resourceClassify){

        try {
            ValidatorUtils.validateEntity(resourceClassify);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }
        resourceClassify.setCreateBy(BaseContextHandler.getUserId());
        resourceClassify.setCreateTime(LocalDateTime.now());
        return classifyService.saveResourceClassify(resourceClassify);
    }

    @ApiOperation("修改分类")
    @PostMapping("/update")
    public ObjectRestResponse update(ResourceClassify resourceClassify){
        resourceClassify.setUpdateBy(BaseContextHandler.getUserId());
        resourceClassify.setUpdateTime(LocalDateTime.now());
        return classifyService.updateResourceClassify(resourceClassify);
    }

    @ApiOperation("删除分类")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return classifyService.deleteResourceClassify(id);
    }

}

