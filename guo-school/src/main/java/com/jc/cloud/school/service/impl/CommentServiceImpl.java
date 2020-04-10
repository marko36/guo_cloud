package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Comment;
import com.jc.cloud.school.mapper.CommentMapper;
import com.jc.cloud.school.service.ICommentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public ObjectRestResponse<Comment> getCommentById(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        Comment comment = this.getById(id);
        return comment == null ? response.error("不存在") : response.ok(comment);
    }

    @Override
    public ObjectRestResponse<List<Comment>> getListByDocumentId(Integer documentId, Page page) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("documentId",documentId);
        return new ObjectRestResponse<Comment>().ok(this.page(page,queryWrapper));
    }

    @Override
    public ObjectRestResponse saveComment(Comment comment) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(comment) ? response.ok() : response.error();
    }

    @Override
    public ObjectRestResponse deleteComment(Integer id) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok() : response.error();
    }

    @Override
    public ObjectRestResponse editStatus(Comment comment) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(comment) ? response.ok("审核保存成功") : response.error("审核保存失败");
    }
}
