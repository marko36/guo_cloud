package com.jc.cloud.school.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.Comment;
import com.jc.cloud.school.service.ICommentService;
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
 * 评论 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-09
 */
@RestController
@RequestMapping("/v1/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @ApiOperation(value = "根据Id获取评论")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse<Comment> getById(Integer id){
        return commentService.getCommentById(id);
    }

    @ApiOperation(value = "根据文章Id获取评论集合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "documentId",value = "文章依赖id编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "pageNumber",value = "当前页",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "limit",value = "一页显示几条数据",required = true,paramType = "query")
            })

    @GetMapping("/getListByDocumentId")
    public ObjectRestResponse<List<Comment>> getListByDocumentId(Integer documentId,Integer pageNumber,Integer limit){
        Page page = new Page(pageNumber,limit);
        return commentService.getListByDocumentId(documentId,page);
    }

    @ApiOperation(value = "保存评论")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "replyId",value = "回复评论id编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "documentId",value = "文章依赖id编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "content",value = "评论内容",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(Comment comment){
        try {
            ValidatorUtils.validateEntity(comment);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }

        comment.setCreateBy(BaseContextHandler.getUserId());
        comment.setCreateTime(LocalDateTime.now());
        return commentService.saveComment(comment);
    }

    @ApiOperation(value = "删除评论")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return commentService.deleteComment(id);
    }

    @ApiOperation(value = "评论审核")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(Comment comment) {
        comment.setUpdateBy(BaseContextHandler.getUserId());
        comment.setUpdateTime(LocalDateTime.now());

        return commentService.editStatus(comment);
    }

}

