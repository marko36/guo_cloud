package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Comment;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-06-09
 */
public interface ICommentService extends IService<Comment> {

    ObjectRestResponse<Comment> getCommentById(Integer id);

    ObjectRestResponse<List<Comment>> getListByDocumentId(Integer documentId, Page page);

    ObjectRestResponse saveComment(Comment comment);

    ObjectRestResponse deleteComment(Integer id);

    ObjectRestResponse editStatus(Comment comment);

}
