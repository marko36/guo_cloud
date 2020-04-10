package com.jc.cloud.home.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.VipService;
import com.jc.cloud.home.service.IVipServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 支付 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-09
 */
@Api("支付管理信息")
@RestController
@RequestMapping("/v1/vipService")
public class VipServiceController {

    @Autowired
    private IVipServiceService vipServiceService;

    @ApiOperation("根据类型查看费用")
    @GetMapping("/getVipType")
    public ObjectRestResponse<List<VipService>> getVipType(Integer type) {
        return new ObjectRestResponse<>().ok(vipServiceService.getVipType(type));
    }

    @ApiOperation("服务购买")
    @PostMapping("/save")
    public ObjectRestResponse<String> saveVipService(VipService vipService) {
        try {
            ValidatorUtils.validateEntity(vipService);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        vipServiceService.save(vipService);
        return new ObjectRestResponse<>().ok("购买成功");
    }
}

