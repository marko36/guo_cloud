package com.jc.cloud.school.api;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.oss.util.OSSUtil;
import com.jc.cloud.school.entity.Activity;
import com.jc.cloud.school.entity.CourseCombination;
import com.jc.cloud.school.service.IActivityService;
import com.jc.cloud.school.util.MySqlBulkInsert;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 特训营 前端控制器
 * </p>
 *
 * @author chenjian
 * @since 2019-05-31
 */
@RestController
@RequestMapping("/v1/activity")
public class ActivityController {
    @Autowired
    IActivityService activityService;

    @ApiOperation(value = "根据Id获取特训营")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @GetMapping("/getById")
    public ObjectRestResponse<Activity> getById(Integer id){
        return activityService.getActivityById(id);
    }

    @ApiOperation(value = "获取特训营集合")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "month",value = "月份数,0为取全部，正数为即将开始的集合，负数为往期回顾",
                            required = true,paramType = "query"),
                    @ApiImplicitParam(name = "pageNumber",value = "当前页",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "limit",value = "一页显示几条数据",required = true,paramType = "query")
            }
    )
    @GetMapping("/getList")
    public ObjectRestResponse<List<Activity>> getList(Integer month,Integer pageNumber,Integer limit){
        Page page = new Page<CourseCombination>(pageNumber,limit);//分页处理
        return activityService.getListBySymbols(month,page);
    }

    @ApiOperation(value = "添加新的特训营")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "cover",value = "封面图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "title",value = "标题",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "presentation",value = "项目介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "lecturer",value = "讲师介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "schedule",value = "日程介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "previous",value = "往期介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "site",value = "地址",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "beginTime",value = "特训开始时间",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "endTime",value = "特训结束时间",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "deadline",value = "报名截止时间",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "numberNum",value = "预算人数",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "applyNumber",value = "报名人数",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "phone",value = "联系电话",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "price",value = "价格",required = true,paramType = "query")
            }
    )
    @PostMapping("/save")
    public ObjectRestResponse save(Activity activity){

        try {
            ValidatorUtils.validateEntity(activity);
        } catch (Exception e) {
            return new ObjectRestResponse().error(e.getMessage());
        }

        activity.setCreateBy(BaseContextHandler.getUserId());
        activity.setCreateTime(LocalDateTime.now());

        return activityService.saveActivity(activity);
    }

    @ApiOperation(value = "删除指定Id的特训营")
    @ApiImplicitParam(name = "id",value = "id编号",required = true,paramType = "query")
    @PostMapping("/delete")
    public ObjectRestResponse delete(Integer id){
        return activityService.delete(id);
    }

    @ApiOperation(value = "更新指定特训营")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "cover",value = "封面图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "title",value = "标题",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "presentation",value = "项目介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "lecturer",value = "讲师介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "schedule",value = "日程介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "previous",value = "往期介绍图片",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "site",value = "地址",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "beginTime",value = "特训开始时间",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "endTime",value = "特训结束时间",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "deadline",value = "报名截止时间",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "numberNum",value = "预算人数",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "applyNumber",value = "报名人数",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "phone",value = "联系电话",required = false,paramType = "query"),
                    @ApiImplicitParam(name = "price",value = "价格",required = false,paramType = "query")
            }
    )
    @PostMapping("/update")
    public ObjectRestResponse update(Activity activity){
        activity.setUpdateBy(BaseContextHandler.getUserId());
        activity.setUpdateTime(LocalDateTime.now());
        return activityService.updateActivity(activity);
    }


    @ApiOperation("修改指定特训营的审核状态")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "id",value = "编号",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "status",value = "审核状态,0待审核,1通过，-1不通过",required = true,paramType = "query")
            }
    )
    @PostMapping("/editStatus")
    public ObjectRestResponse editStatus(Activity activity){
        activity.setUpdateBy(BaseContextHandler.getUserId());
        activity.setUpdateTime(LocalDateTime.now());
        return activityService.editStatus(activity);
    }

    @Autowired
    private OSSUtil ossUtil;

    @ApiOperation("上传图片")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "inputStream",value = "图片",required = true,paramType = "query"),
                    @ApiImplicitParam(name = "belong",value = "图片归属(1.presentation 2.lecturer 3.schedule 4.previous) ",
                            required = true,paramType = "query"),
            }
    )
    @PostMapping("/uploading")
    public String test(MockMultipartFile file, Integer belongNum){

        //MultipartFile multipartFile = new MockMultipartFile(file);

        String bucketName = "guo-img";//存储空间名称
        String[] belong = {"presentation","lecturer","schedule","previous"};//图片名称

        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf('.'));//获取文件后缀

        String path = "guo-school/activity/" + BaseContextHandler.getUserId()+"/"+belong[belongNum]+suffix;//组合成在OSS里的存储地址

        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossUtil.uploadByInputStream(inputStream,bucketName,path);
        return path;
    }

    @ApiOperation("插入随机数据")
    @ApiImplicitParam(name = "num",value = "插入随机数据数量",required = true,paramType = "query")
    @PostMapping("/bulkInsert")
    public ObjectRestResponse bulkInsert(Integer num){
        MySqlBulkInsert bulkInsert = new MySqlBulkInsert();
        ObjectRestResponse response = new ObjectRestResponse();
        List<Activity> activities = bulkInsert.bulkInsert(num);
        return activityService.saveBatch(activities) ? response.ok("成功"):response.error("失败");
    }

}

