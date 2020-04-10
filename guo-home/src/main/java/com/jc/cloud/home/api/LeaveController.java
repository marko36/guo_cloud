package com.jc.cloud.home.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Leave;
import com.jc.cloud.home.service.ILeaveService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-19
 */
@RestController
@RequestMapping("/v1/leave")
public class LeaveController {

    @Autowired
    public ILeaveService leaveService;

    @ApiOperation("请假列表")
    @GetMapping("/list")
    public ObjectRestResponse<List<Leave>> getAll() {
        return new ObjectRestResponse<>().ok(leaveService.list());
    }

    @ApiOperation("请假")
    @PostMapping("/save")
    public ObjectRestResponse<String> save(Leave leave) {
        try {
            ValidatorUtils.validateEntity(leave);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        leave.setBabyId(BaseContextHandler.getBabyId());
        leaveService.save(leave);
        return new ObjectRestResponse<>().ok("添加成功");
    }

    @ApiOperation("更新请假")
    @PostMapping("/update")
    public ObjectRestResponse<String> update(Leave leave) {
        leaveService.updateById(leave);
        return new ObjectRestResponse<>().ok("更新成功");
    }
}

