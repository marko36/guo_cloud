package com.jc.cloud.school.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.Document;
import com.jc.cloud.school.service.IDocumentService;
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
 * 文档 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/v1/document")
public class DocumentController {

    @Autowired
    IDocumentService documentService;

    @ApiOperation(value = "根据Id获取资源文件")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse<Document> getById(Integer id){
        return documentService.getDocumentById(id);
    }

    @ApiOperation(value = "根据类型条件等获取资源文件集合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "type",value = "文件类型,1.文章  2.视频  3文档",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "target",value = "资源面向对象,1.全部 2.仅限家长 3.权限园区 ",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "classifyId",value = "分类编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "暂时用来排序，没有就默认,1时间正序,2时间倒序,3览(点击)从多到少,4收藏数从多到少",
                            required = false,paramType = "query"),
                    @ApiImplicitParam(name = "type",value = "文件类型,1.文章  2.视频  3文档",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "pageNumber",value = "页码，第几页",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "limit",value = "一页要几条信息",required = true,paramType = "query")
            }
    )
    @GetMapping("/getListByDocument")
    public ObjectRestResponse<List<Document>> getListByDocument(Document document,Integer pageNumber,Integer limit){
        return documentService.getListByDocument(document,new Page<Document>(pageNumber,limit));
    }

    @ApiOperation(value = "根据名称、类型获取资源文件集合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "title",value = "标题,名称",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "type",value = "文件类型,1.文章  2.视频  3文档",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "pageNumber",value = "页码，第几页",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "limit",value = "一页要几条信息",required = true,paramType = "query")
            }
    )
    @GetMapping("/getListByTitle")
    public ObjectRestResponse<List<Document>> getListByTitle(Document document,Integer pageNumber,Integer limit){
        return documentService.getListByTitle(document,new Page<Document>(pageNumber,limit));
    }

    @ApiOperation(value = "创建新的资源文件")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "title",value = "标题,名称",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "type",value = "文件类型,1.文章  2.视频  3文档",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "target",value = "资源面向对象,1.全部 2.仅限家长 3.权限园区 ",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "classifyId",value = "分类编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "type",value = "文件类型,1.文章  2.视频  3文档",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "content",value = "介绍",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "source",value = "资源地址",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面图片地址",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "time",value = "视频播放时间",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "score",value = "积分",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse<Document> save(Document document){
        try {
            ValidatorUtils.validateEntity(document);
        } catch (Exception e) {
            return new ObjectRestResponse<Document>().error(e.getMessage());
        }

        document.setCreateBy(BaseContextHandler.getUserId());
        document.setCreateTime(LocalDateTime.now());
        return documentService.saveDocument(document);
    }

    @ApiOperation(value = "删除指定Id的资源文件")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return documentService.delete(id);
    }

    @ApiOperation(value = "更新指定资源文件")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "title",value = "标题,名称",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "type",value = "文件类型,1.文章  2.视频  3文档",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "target",value = "资源面向对象,1.全部 2.仅限家长 3.权限园区 ",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "classifyId",value = "分类编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "type",value = "文件类型,1.文章  2.视频  3文档",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "content",value = "介绍",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "source",value = "资源地址",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面图片地址",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "time",value = "视频播放时间",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "score",value = "积分",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(Document document){
        document.setUpdateBy(BaseContextHandler.getUserId());
        document.setUpdateTime(LocalDateTime.now());
        return documentService.updateDocument(document);
    }

    @ApiOperation(value = "审核指定资源文件")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(Document document){
        document.setUpdateBy(BaseContextHandler.getUserId());
        document.setUpdateTime(LocalDateTime.now());
        return documentService.editStatus(document);
    }

}

