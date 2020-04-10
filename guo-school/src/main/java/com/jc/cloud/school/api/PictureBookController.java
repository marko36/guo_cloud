package com.jc.cloud.school.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.school.entity.PictureBook;
import com.jc.cloud.school.service.IPictureBookService;
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
 * 绘本 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-06-14
 */
@Controller
@RequestMapping("/v1/pictureBook")
public class PictureBookController {
    @Autowired
    IPictureBookService pictureBookService;

    @ApiOperation("获取绘本")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse getById(Integer id){
        return pictureBookService.getPictureBookById(id);
    }

    @ApiOperation("根据绘本组合编号获取绘本集合")
    @ApiImplicitParam(name = "combinationId",value = "绘本组合id编号",required = true,paramType = "query")
    @GetMapping("/getListBy")
    public ObjectRestResponse getListByCombinationID(Integer combinationID){
        return pictureBookService.getListByCombinationID(combinationID);
    }

    @ApiOperation("增加保存绘本")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "picture",value = "封面图片",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "ChineseSentence",value = "中文语句",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "EnglishSentence",value = "英文语句",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "voiceFrequency",value = "音频地址",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(PictureBook pictureBook){

        try {
            ValidatorUtils.validateEntity(pictureBook);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }
        pictureBook.setCreateBy(BaseContextHandler.getUserId());
        pictureBook.setCreateTime(LocalDateTime.now());
        return pictureBookService.savePictureBook(pictureBook);
    }

    @ApiOperation("修改绘本")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "picture",value = "封面图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "ChineseSentence",value = "中文语句",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "EnglishSentence",value = "英文语句",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "voiceFrequency",value = "音频地址",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(PictureBook pictureBook){
        pictureBook.setUpdateBy(BaseContextHandler.getUserId());
        pictureBook.setUpdateTime(LocalDateTime.now());
        return pictureBookService.updatePictureBook(pictureBook);
    }

    @ApiOperation("审核绘本")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(PictureBook pictureBook){
        pictureBook.setUpdateBy(BaseContextHandler.getUserId());
        pictureBook.setUpdateTime(LocalDateTime.now());
        return pictureBookService.editStatus(pictureBook);
    }

    @ApiOperation("删除绘本")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return pictureBookService.deletePictureBook(id);
    }
}

