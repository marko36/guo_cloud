package com.jc.cloud.shop.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.common.util.Query;
import com.jc.cloud.shop.entity.Goods;
import com.jc.cloud.shop.service.IGoodsService;
import com.jc.cloud.vo.shop.GoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author lgh
 * @since 2019-05-24
 */
@RestController
@RequestMapping("/v1/goods")
public class GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    IGoodsService goodsService;

    @ApiOperation(value = "发布商品")
    @PostMapping("/saveGoods")
    public ObjectRestResponse<Goods> saveGoods(Goods goods){
        return goodsService.saveGoods(goods);
    }

    @ApiOperation(value = "审核商品")
    @PostMapping("/updateAuditStatus")
    public ObjectRestResponse<Goods> updateAuditStatus(Goods goods){
        goods.setUpdateBy(BaseContextHandler.getUserName());
        goodsService.updateById(goods);
        log.info("ok");
        return new ObjectRestResponse<Goods>().ok("审核成功");
    }

    @ApiOperation(value = "上/下架商品")
    @PostMapping("/updatePublishStatus")
    public ObjectRestResponse<Goods> updatePublishStatus(Goods goods){
        return goodsService.updateGoods(goods);
    }

    @ApiOperation(value = "获取所有上架商品中规格最低价格商品的信息")
    @GetMapping("/getPublishGoodsInfo")
    public ObjectRestResponse<List<GoodsVo>> getPublishGoodsInfo(){
        return goodsService.getPublishGoodsInfo();
    }

    @ApiOperation(value = "分页查看商品")
    @GetMapping("/viewPage")
    public ObjectRestResponse<GoodsVo> viewPage(){
        return goodsService.viewPage();
    }

    @ApiOperation(value = "获取一个商品全部规格所有信息")
    @GetMapping("/getPublishGoodsByGoodsId")
    public ObjectRestResponse<List<GoodsVo>> getPublishGoodsByGoodsId(String goodsId){
        if (goodsId == null){
            return new ObjectRestResponse<>().error("商品id不能为空");
        }
        return goodsService.getPublishGoodsByGoodsId(goodsId);
    }

    @ApiOperation(value = "根据商品id和规格获取商品信息")
    @PostMapping("/getByGoodsIdAndGoodsAttribute")
    public ObjectRestResponse<GoodsVo> getByGoodsIdAndGoodsAttribute(String goodsId,String goodsAttribute){
        if (goodsId == null || goodsAttribute == null){
            return new ObjectRestResponse<>().error("商品id或商品规格不能为空");
        }
        return new ObjectRestResponse<>().ok(goodsService.getByGoodsIdAndGoodsAttribute(goodsId,goodsAttribute));
    }

}

