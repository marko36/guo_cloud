package com.jc.cloud.shop.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.shop.entity.BrandInfo;

import com.jc.cloud.shop.service.IBrandInfoService;
import com.jc.cloud.vo.shop.BrandVo;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * <p>
 * 品牌详情表 前端控制器
 * </p>
 *
 * @author lgh
 * @since 2019-05-23
 */
@RestController
@RequestMapping("/v1/brand")
public class BrandInfoController {
    private static final Logger log = LoggerFactory.getLogger(BrandInfoController.class);

    @Autowired
    IBrandInfoService brandInfoService;

    @ApiOperation(value = "添加品牌")
    @PostMapping("/addBrand")
    public ObjectRestResponse<BrandInfo> addBrand(BrandInfo brandInfo){
        return brandInfoService.addBrand(brandInfo);
    }

    @ApiOperation(value = "删除品牌")
    @GetMapping("/delBrand")
    public  ObjectRestResponse<BrandInfo> delete(Integer Id){
        return brandInfoService.delete(Id);
    }

}

