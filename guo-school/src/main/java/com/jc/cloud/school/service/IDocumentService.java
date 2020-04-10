package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.Document;

import java.util.List;

/**
 * <p>
 * 文档 服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
public interface IDocumentService extends IService<Document> {

    ObjectRestResponse<Document> getDocumentById(Integer id);

    ObjectRestResponse<List<Document>> getListByTitle(Document document, Page page);

    ObjectRestResponse<List<Document>> getListByDocument(Document document, Page page);

    ObjectRestResponse saveDocument(Document document);

    ObjectRestResponse updateDocument(Document document);

    ObjectRestResponse editStatus(Document document);

    ObjectRestResponse delete(Integer id);

}
