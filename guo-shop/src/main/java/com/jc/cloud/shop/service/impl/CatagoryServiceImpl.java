package com.jc.cloud.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.shop.entity.Catagory;
import com.jc.cloud.shop.mapper.CatagoryMapper;
import com.jc.cloud.shop.service.ICatagoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
@Service
public class CatagoryServiceImpl extends ServiceImpl<CatagoryMapper, Catagory> implements ICatagoryService {

    @Autowired
    CatagoryMapper catagoryMapper;


    @Override
    public List<Catagory> listAll() {
        return this.list();
    }


    @Override
    @Transactional
    public ObjectRestResponse<Catagory> delete(Integer Id) {
        if (Id == null){
            return new ObjectRestResponse<>().error("分类id不能为空");
        }
        Catagory catagory = getById(Id);
        if (catagory == null){
            return new ObjectRestResponse<>().error("没有该目录");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parentId",Id);
        List<Catagory> list = list(queryWrapper);
        if (list != null && list.size() > 0){
            return new ObjectRestResponse<>().error("不能删除拥有子目录的目录");
        }
        removeById(Id);
        return new ObjectRestResponse<>().ok("删除成功");
    }

    @Override
    @Transactional
    public ObjectRestResponse<Catagory> addCatagory(Catagory catagory) {
        try {
            ValidatorUtils.validateEntity(catagory);
            boolean result = this.save(catagory);
            if (!result){
                return new ObjectRestResponse<>().error("操作失败");
            }
            return new ObjectRestResponse<>().ok("添加成功");
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }
    }

    @Override
    public ObjectRestResponse<Catagory> updateCatagory(Catagory catagory) {
        try {
            ValidatorUtils.validateEntity(catagory);
            catagory.setUpdateBy(BaseContextHandler.getUserName());
            catagory.setUpdateTime(new Date());
            boolean result = this.updateById(catagory);
            if (!result){
                return new ObjectRestResponse<>().error("更新失败");
            }
            return new ObjectRestResponse<>().ok("更新成功");
        }catch (Exception e){
            return new ObjectRestResponse<>().error(e.getMessage());
        }
    }


}
