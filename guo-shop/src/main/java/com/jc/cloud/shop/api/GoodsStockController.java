package com.jc.cloud.shop.api;


import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.entity.GoodsStock;
import com.jc.cloud.shop.service.IGoodsStockService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品库存量表 前端控制器
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@RestController
@RequestMapping("/v1/stock")
public class GoodsStockController {

    @Autowired
    IGoodsStockService stockService;

    @ApiOperation(value = "添加库存")
    @PostMapping("/saveStock")
    public ObjectRestResponse<GoodsStock> saveStock(GoodsStock stock){
        return stockService.saveStock(stock);
    }

    @ApiOperation(value = "根据商品id获取所有库存")
    @PostMapping("/getAllByGoodsId")
    public ObjectRestResponse<Object> getAllByGoodsId(String goodsId){
        return stockService.getAllByGoodsId(goodsId);
    }

    @ApiOperation(value = "根据商品的规格获取库存")
    @PostMapping("/getByGoodsAttribute")
    public ObjectRestResponse<Object> getByGoodsAttribute(String goodsId,String goodsAttribute){
        return stockService.getByGoodsAttribute(goodsId,goodsAttribute);
    }

}

