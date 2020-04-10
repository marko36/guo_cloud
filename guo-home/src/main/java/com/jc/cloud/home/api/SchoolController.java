package com.jc.cloud.home.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.School;
import com.jc.cloud.home.service.ISchoolService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@RestController
@RequestMapping("/v1/school")
public class SchoolController {

    @Autowired
    private ISchoolService schoolService;

    @ApiOperation("查询未审核的学校信息")
    @GetMapping("/notAudited")
    public ObjectRestResponse<List<School>> notAudited(boolean activity) {
        return new ObjectRestResponse<>().ok(schoolService.getAll(activity));
    }

    @ApiOperation("根据userId查询学校")
    @GetMapping(value = "/getByUid")
    public ObjectRestResponse<School> getByUserId() {
        return new ObjectRestResponse<>().ok(schoolService.getByUserId(BaseContextHandler.getUserId()));
    }

    @ApiOperation("创建校园")
    @PostMapping("/save")
    public ObjectRestResponse<String> save(School school) {
        try {
            ValidatorUtils.validateEntity(school);
        } catch (Exception e) {
            return new ObjectRestResponse<>().ok(e.getMessage());
        }
        school.setUid(BaseContextHandler.getUserId());
        schoolService.save(school);
        return new ObjectRestResponse<>().ok("添加成功");
    }

    @ApiOperation("校园更新")
    @PostMapping(value = "/update")
    public ObjectRestResponse<String> update(School school) {
        schoolService.updateById(school);
        return new ObjectRestResponse<>().ok("更新成功");
    }

    @ApiOperation("学校审核")
    @PostMapping(value = "/audit")
    public ObjectRestResponse<String> audit(String id, boolean activity) {
        schoolService.audit(id, activity);
        return new ObjectRestResponse<String>().ok("审核成功");
    }
}

