package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.Resource;
import com.jc.cloud.school.service.IResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/resource")
public class ResourceController {

    @Autowired
    IResourceService resourceService;

    @ApiOperation("根据Id获取资源")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse getById(Integer id){
        return resourceService.getResourceById(id);
    }

    @ApiOperation("根据条件获取资源集合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "名称",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "gradingId",value = "分级编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "themeId",value = "主题编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "pageNumber",value = "页码，第几页",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "limit",value = "一页要几条信息",required = true,paramType = "query")
            }
    )
    @GetMapping("/getListByResource")
    public ObjectRestResponse getListByResource(Resource resource){
        return resourceService.getListByResource(resource);
    }

    @ApiOperation("增加/保存资源")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "名称",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "gradingId",value = "分级编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "themeId",value = "主题编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面地址",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "path",value = "资源地址",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(Resource resource){
        try {
            ValidatorUtils.validateEntity(resource);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }
        resource.setCreateBy(BaseContextHandler.getUserId());
        resource.setCreateTime(LocalDateTime.now());
        return resourceService.saveResource(resource);
    }

    @ApiOperation("修改资源")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "name",value = "名称",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "gradingId",value = "分级编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "themeId",value = "主题编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面地址",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "path",value = "资源地址",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(Resource resource){
        resource.setUpdateBy(BaseContextHandler.getUserId());
        resource.setUpdateTime(LocalDateTime.now());
        return resourceService.updateResource(resource);
    }

    @ApiOperation("修改资源")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(Resource resource){
        resource.setUpdateBy(BaseContextHandler.getUserId());
        resource.setUpdateTime(LocalDateTime.now());
        return resourceService.editStatus(resource);
    }

    @ApiOperation("删除资源")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return resourceService.deleteResource(id);
    }

}

