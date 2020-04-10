package com.jc.cloud.school.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.DocumentClassify;
import com.jc.cloud.school.service.IDocumentClassifyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/v1/documentClassify")
public class DocumentClassifyController {

    @Autowired
    IDocumentClassifyService classifyService;

    @ApiOperation(value = "根据分类编号获取分类")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse<DocumentClassify> getDocumentClassifyById(Integer id){
        return classifyService.getDocumentClassifyById(id);
    }

    @ApiOperation(value = "根据上级分类编号获取分类集合")
    @ApiImplicitParam(name = "parentId",value = "上级分类id编号",required = true,paramType = "query")
    @GetMapping("/getListByParentId")
    public ObjectRestResponse<List<DocumentClassify>> getListByParentId(Integer parentId){
        return classifyService.getListByParentId(parentId);
    }

    @ApiOperation(value = "获取所有公开分类集合")
    @GetMapping("/getListAll")
    public ObjectRestResponse<List<DocumentClassify>> getListAll(){
        return classifyService.getListAll();
    }

    @ApiOperation(value = "增加分类")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "名称",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "type",value = "类型 1私密 2公开",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "visibles",value = "私密授权园所",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "parentId",value = "父级编号",required = false,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(DocumentClassify classify){
        try {
            ValidatorUtils.validateEntity(classify);
        } catch (Exception e) {
            return new ObjectRestResponse<DocumentClassify>().error(e.getMessage());
        }
        classify.setCreateBy(classify.getCreateBy());
        classify.setCreeateTime(LocalDateTime.now());
        return classifyService.saveDocumentClassify(classify);
    }

    @ApiOperation(value = "更新分类")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "name",value = "名称",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "type",value = "类型 1私密 2公开",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "visibles",value = "私密授权园所",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "parentId",value = "父级编号",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(DocumentClassify classify){
        classify.setUpdateBy(classify.getUpdateBy());
        classify.setUpdateTime(LocalDateTime.now());
        return classifyService.updateDocumentClassify(classify);
    }

    @ApiOperation(value = "删除分类,连带分类底下的二级分类都会删除")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return classifyService.delete(id);
    }



}

