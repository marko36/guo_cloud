package com.jc.cloud.school.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.school.entity.PicturebookCombination;
import com.jc.cloud.school.service.IPicturebookCombinationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 绘本组合 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/picturebookCombination")
public class PicturebookCombinationController {

    @Autowired
    IPicturebookCombinationService combinationService;

    @ApiOperation("获取绘画组合")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/get")
    public ObjectRestResponse getById(Integer id){
        return combinationService.getPicturebookCombinationById(id);
    }

    @ApiOperation("根据要求获取绘画组合的集合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "gradingId",value = "分级编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "themeId",value = "主题编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "title",value = "标题",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "pageNumber",value = "页码，第几页",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "limit",value = "一页要几条信息",required = true,paramType = "query")
            }
    )
    @GetMapping("/getList")
    public ObjectRestResponse getListByPicturebookCombination(PicturebookCombination combination,Integer pageNumber,Integer limit){
        Page page = new Page(pageNumber,limit);
        return combinationService.getListByPicturebookCombination(combination,page);
    }

    @ApiOperation("增加/保存绘画组合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "gradingId",value = "分级编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "themeId",value = "主题编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面地址",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "title",value = "标题",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(PicturebookCombination combination){
        return combinationService.savePicturebookCombination(combination);
    }

    @ApiOperation("修改绘画组合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "gradingId",value = "分级编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "themeId",value = "主题编号",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面地址",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "title",value = "标题",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(PicturebookCombination combination){
        return combinationService.updatePicturebookCombination(combination);
    }

    @ApiOperation("审核绘画组合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(PicturebookCombination combination){
        return combinationService.editStatus(combination);
    }

    @ApiOperation("删除绘画组合")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse save(Integer id){
        return combinationService.deletePicturebookCombination(id);
    }

}

