package com.jc.cloud.home.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Baby;
import com.jc.cloud.home.service.IBabyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 宝宝 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@Api("宝宝信息管理")
@RestController
@RequestMapping("/v1/baby")
public class BabyController {


    @Autowired
    private IBabyService babyService;

    @ApiOperation("根据用户Id查询宝宝信息")
    @GetMapping("/getActiviyBabyByUid")
    public ObjectRestResponse<Baby> getActiviyBabyByUid() {
        return new ObjectRestResponse<>().ok(babyService.getActiviyBabyByUid(BaseContextHandler.getUserId()));
    }

    @ApiOperation("新增宝宝信息")
    @PostMapping(value = "/save")
    public ObjectRestResponse<String> saveBaby(Baby baby) {
        try {
            ValidatorUtils.validateEntity(baby);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        baby.setUid(BaseContextHandler.getUserId());
        baby.setSchoolId(BaseContextHandler.getSchoolId());
        babyService.save(baby);
        return new ObjectRestResponse<>().ok("添加成功");
    }

    @ApiOperation("删除宝宝")
    @PostMapping("/delete")
    public ObjectRestResponse<String> deleteBaby(String id) {
        babyService.removeById(id);
        return new ObjectRestResponse<>().ok("删除成功");
    }

    @ApiOperation("更新宝宝信息")
    @PostMapping(value = "/update")
    public ObjectRestResponse<String> updateBaby(Baby baby) {
        babyService.updateById(baby);
        return new ObjectRestResponse<>().ok("更新成功");
    }

    @ApiOperation("宝宝审核通过")
    @PostMapping(value = "/audit")
    public ObjectRestResponse<String> babyAudit(String id, Integer examine) {
        babyService.babyAudit(id, examine);
        return new ObjectRestResponse<>().ok("审核通过");
    }

    @ApiOperation("切换宝宝激活状态")
    @PostMapping(value = "/activiy")
    public ObjectRestResponse<String> switchActiviy(String id, Boolean activity) {
        babyService.switchActiviy(id, activity);
        return new ObjectRestResponse<>().ok("激活成功");
    }

}

