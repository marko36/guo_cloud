package com.jc.cloud.home.service.impl;

import com.jc.cloud.home.entity.Recipes;
import com.jc.cloud.home.mapper.RecipesMapper;
import com.jc.cloud.home.service.IRecipesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 食谱 服务实现类
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Service
public class RecipesServiceImpl extends ServiceImpl<RecipesMapper, Recipes> implements IRecipesService {

    @Autowired private RecipesMapper recipesMapper;

    @Override
    public List<Recipes> weeks() {
        return recipesMapper.weeks();
    }

    @Override
    public List<Recipes> lastWeek() {
        return recipesMapper.lastWeek();
    }

    @Override
    public List<Recipes> lowerWeek() {
        return recipesMapper.lowerWeek();
    }
}
