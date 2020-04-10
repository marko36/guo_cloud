package com.jc.cloud.home.service;

import com.jc.cloud.home.entity.Recipes;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 食谱 服务类
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
public interface IRecipesService extends IService<Recipes> {

    // 获取本周的科学食谱
    List<Recipes> weeks();

    // 获取上一周的科学食谱
    List<Recipes> lastWeek();

    // 获取下一周的科学食谱
    List<Recipes> lowerWeek();
}
