package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.Recipes;
import com.jc.cloud.home.service.IRecipesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 食谱 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@RestController
@RequestMapping("/v1/recipes")
public class RecipesController {

    @Autowired
    private IRecipesService recipesService;

    @ApiOperation("获取本周的科学食谱")
    @GetMapping("/weeks")
    public ObjectRestResponse<List<Recipes>> weeks() {
        return new ObjectRestResponse<>().ok(recipesService.weeks());
    }

    @ApiOperation("获取上一周的科学食谱")
    @GetMapping("/lastWeek")
    public ObjectRestResponse<List<Recipes>> lastWeek() {
        return new ObjectRestResponse<>().ok(recipesService.lastWeek());
    }

    @ApiOperation("获取下一周的科学食谱")
    @GetMapping("/lowerWeek")
    public ObjectRestResponse<List<Recipes>> lowerWeek() {
        return new ObjectRestResponse<>().ok(recipesService.lowerWeek());
    }
}

