package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.Theme;
import com.jc.cloud.school.service.IThemeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * <p>
 * 主题 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/theme")
public class ThemeController {

    @Autowired
    IThemeService themeService;

    @ApiOperation("获取主题")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse getById(Integer id){
        return themeService.getThemeById(id);
    }

    @ApiOperation("获取全部主题")
    @GetMapping("/getListAll")
    public ObjectRestResponse getListAll(){
        return themeService.getListAll();
    }


    @ApiOperation("增加保存主题")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "名称,主题",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(Theme theme){
        try {
            ValidatorUtils.validateEntity(theme);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }
        theme.setCreateBy(BaseContextHandler.getUserId());
        theme.setCreateTime(LocalDateTime.now());
        return themeService.saveTheme(theme);
    }

    @ApiOperation("修改主题")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "name",value = "名称,主题",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(Theme themeg){
        themeg.setUpdateBy(BaseContextHandler.getUserId());
        themeg.setUpdateTime(LocalDateTime.now());
        return themeService.updateTheme(themeg);
    }

    @ApiOperation("删除主题")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return themeService.deleteTheme(id);
    }
}

