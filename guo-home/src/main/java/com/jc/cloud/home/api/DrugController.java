package com.jc.cloud.home.api;


import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.ValidatorUtils;
import com.jc.cloud.home.entity.Drug;
import com.jc.cloud.home.service.IDrugService;
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
 * 服药 前端控制器
 * </p>
 *
 * @author lq
 * @since 2019-06-20
 */
@Api("服药信息管理")
@RestController
@RequestMapping("/v1/drug")
public class DrugController {

    @Autowired
    private IDrugService drugService;

    @ApiOperation("服药记录")
    @GetMapping("/list")
    public ObjectRestResponse<List<Drug>> getAll() {
        return new ObjectRestResponse<>().ok(drugService.list());
    }

    @ApiOperation("服药")
    @PostMapping("/save")
    public ObjectRestResponse<String> save(Drug drug) {
        try {
            ValidatorUtils.validateEntity(drug);
        } catch (Exception e) {
            return new ObjectRestResponse<>().error(e.getMessage());
        }
        drug.setUid(BaseContextHandler.getUserId());
        drugService.save(drug);
        return new ObjectRestResponse<>().ok("服药");
    }
}

