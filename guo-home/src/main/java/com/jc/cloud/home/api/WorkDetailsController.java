package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.home.entity.WorkDetails;
import com.jc.cloud.home.service.IWorkDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 行驶记录 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-05
 */
@Api("行驶记录管理信息")
@RestController
@RequestMapping("/v1/workDetails")
public class WorkDetailsController {

    @Autowired private IWorkDetailsService workDetailsService;

    @ApiOperation("/查看行驶记录")
    @GetMapping("/list")
    public ObjectRestResponse<List<WorkDetails>> getAll(){
        return new ObjectRestResponse<>().ok(workDetailsService.list());
    }
}

