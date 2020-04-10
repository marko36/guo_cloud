package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Document;
import com.jc.cloud.school.entity.DocumentClassify;
import com.jc.cloud.school.mapper.DocumentMapper;
import com.jc.cloud.school.service.IDocumentClassifyService;
import com.jc.cloud.school.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文档 服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements IDocumentService {
    @Autowired
    IDocumentClassifyService classifyService;

    @Override
    public ObjectRestResponse<Document> getDocumentById(Integer id) {
        ObjectRestResponse<Document> response = new ObjectRestResponse<Document>();
        Document document = this.getById(id);
        return document == null ? response.error("没有这个资源文件") : response.ok(document);
    }

    /**
     * 根据资源标题和资源类型获取资源集合
     * @param document
     * @return
     */
    @Override
    public ObjectRestResponse<List<Document>> getListByTitle(Document document, Page page){
        ObjectRestResponse<List<Document>> response = new ObjectRestResponse<List<Document>>();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("title",document.getTitle());

        if(document.getType() == null || "".equals(document.getType()) ){
            queryWrapper.eq("type",document.getType());
        }

        return response.ok(this.page(page,queryWrapper).getRecords());
    }

    /**
     * 根据资源分类类型，资源类型等条件获取资源集合
     * @param document  条件
     * @return
     */
    @Override
    public ObjectRestResponse<List<Document>> getListByDocument(Document document, Page page) {
        ObjectRestResponse<List<Document>> response = new ObjectRestResponse();
        QueryWrapper<Document> queryWrapper = new QueryWrapper();

        if(document.getType() != 0) {//判断文件类型，如果没有要求就是全部(默认)
            queryWrapper.eq("type", document.getType());
        }

        //判断资源面向的群体,1是全部
        queryWrapper.eq("target",document.getTarget() == null ? 1 : document.getTarget());

        //标签暂时不知道怎么处理
        //分类查找，如果不是查全部的话
        List<Integer> ids = new ArrayList<>();
        ids.add(document.getClassifyId());

        if( ids.size()>0 && !ids.get(0).equals(0)){
            QueryWrapper<DocumentClassify> classifyWrapper = new QueryWrapper();
            classifyWrapper.select("id")
                    .in("id",ids)
                    .or()
                    .in("parentId",ids);
            queryWrapper.in("classifyId",classifyService.listObjs(classifyWrapper));
        }

        //没有排序的属性，先暂时使用status
        Integer order = document.getStatus();
        if(order != null){//没有就是默认排序
            if(order == 1)
                queryWrapper.orderByAsc("createTime");//时间正序，旧的在前
            else if(order == 2)
                queryWrapper.orderByDesc("createTime");//时间倒序，新的在前
            else if(order == 3)
                queryWrapper.orderByDesc("clickCount");//浏览(点击)从多到少
            else if(order == 4)
                queryWrapper.orderByDesc("collectCount");//收藏数从多到少
        }

        return response.ok(this.page(page,queryWrapper).getRecords());
    }

    @Override
    public ObjectRestResponse saveDocument(Document document) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(document) ? response.ok("添加成功") : response.error("添加失败");
    }

    @Override
    public ObjectRestResponse updateDocument(Document document) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(document) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse editStatus(Document document) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(document) ? response.ok("审核保存成功") : response.error("审核保存失败");
    }

    @Override
    public ObjectRestResponse delete(Integer id){
        ObjectRestResponse response = new ObjectRestResponse();
        return this.removeById(id) ? response.ok("删除成功") : response.error("删除失败");
    }

}
