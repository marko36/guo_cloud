package com.jc.cloud.school.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.TreeUtil;
import com.jc.cloud.school.entity.DocumentClassify;
import com.jc.cloud.school.mapper.DocumentClassifyMapper;
import com.jc.cloud.school.service.IDocumentClassifyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@Service
public class DocumentClassifyServiceImpl extends ServiceImpl<DocumentClassifyMapper, DocumentClassify> implements IDocumentClassifyService {

    @Override
    public ObjectRestResponse<DocumentClassify> getDocumentClassifyById(Integer id) {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.select("id,name,type,parentId,path").eq("id",id);
//        queryWrapper.eq("type",2);
        ObjectRestResponse response = new ObjectRestResponse();
        DocumentClassify classify = this.getById(id);


        return classify == null ? response.error("没有这个分类") : response.ok(classify);
    }

    @Override
    public ObjectRestResponse<List<DocumentClassify>> getListByParentId(Integer parentId){
         QueryWrapper wrapper = new QueryWrapper();
         wrapper.select("id,name,type,parentId,path").eq("parentId",parentId);
         wrapper.eq("type",2);
         return new ObjectRestResponse<List<DocumentClassify>>().ok(TreeUtil.bulid(this.list(wrapper),0));
    }

    @Override
    public ObjectRestResponse<List<DocumentClassify>> getListAll() {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.select("id,name,type,parentId,path");
        List<DocumentClassify> classifyList = this.list();
        return new ObjectRestResponse<List<DocumentClassify>>().ok(TreeUtil.bulid(classifyList,0));
    }

    @Override
    public ObjectRestResponse saveDocumentClassify(DocumentClassify classify) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.save(classify) ? response.ok("保存成功") : response.error("保存失败");
    }

    @Override
    public ObjectRestResponse updateDocumentClassify(DocumentClassify classify) {
        ObjectRestResponse response = new ObjectRestResponse();
        return this.updateById(classify) ? response.ok("修改成功") : response.error("修改失败");
    }

    @Override
    public ObjectRestResponse delete(Integer id){
        ObjectRestResponse response = new ObjectRestResponse();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("id").eq("parentId",id);
        List<Integer> idList = this.listObjs(queryWrapper);
        idList.add(id);

        return this.removeByIds(idList) ? response.ok("删除成功") : response.error("删除失败");
    }


}
