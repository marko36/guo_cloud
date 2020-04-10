package com.jc.cloud.school.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.DocumentClassify;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
public interface IDocumentClassifyService extends IService<DocumentClassify> {

    ObjectRestResponse<DocumentClassify> getDocumentClassifyById(Integer id);

    ObjectRestResponse<List<DocumentClassify>> getListByParentId(Integer parentId);

    ObjectRestResponse<List<DocumentClassify>> getListAll();

    ObjectRestResponse saveDocumentClassify(DocumentClassify documentClassify);

    ObjectRestResponse updateDocumentClassify(DocumentClassify documentClassify);

    ObjectRestResponse delete(Integer id);

}
