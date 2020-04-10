package com.jc.cloud.shop.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.entity.GoodsComment;
import com.jc.cloud.shop.service.IGoodsCommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@RestController
@RequestMapping("/v1/goodsComment")
public class GoodsCommentController {

    @Autowired
    IGoodsCommentService commentService;

    @ApiOperation(value = "添加评论")
    @RequestMapping(value = "/saveComment",method = RequestMethod.POST)
    public ObjectRestResponse<GoodsComment> saveComment(@RequestBody GoodsComment comment, @RequestBody Goods goods){
        comment.setGoodsId(goods.getGoodsId());
        comment.setCreateBy(BaseContextHandler.getUserName());
        comment.setUserId(BaseContextHandler.getUserId());
        commentService.save(comment);
        return new ObjectRestResponse<GoodsComment>().ok("添加成功，等待审核中");
    }

}

