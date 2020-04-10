package com.jc.cloud.shop.service;

import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.Catagory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
public interface ICatagoryService extends IService<Catagory> {

    List<Catagory> listAll();

    ObjectRestResponse<Catagory> delete(Integer Id);

    ObjectRestResponse<Catagory> addCatagory(Catagory catagory);

    ObjectRestResponse<Catagory> updateCatagory(Catagory catagory);
}
