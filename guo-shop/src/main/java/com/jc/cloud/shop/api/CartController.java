package com.jc.cloud.shop.api;

import com.jc.cloud.common.context.BaseContextHandler;
import com.jc.cloud.common.msg.ObjectRestResponse;
import com.jc.cloud.shop.service.ICartService;
import com.jc.cloud.vo.shop.CartVo;
import com.jc.cloud.vo.shop.GoodsVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    ICartService cartService;
    /**
     * 加入购物车
     * @param
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加进购物车")
    public ObjectRestResponse<Object> add(String goodsId,String goodsAttribute,Integer number){
        if (goodsId == null || goodsAttribute == null || number == null){
            return new ObjectRestResponse<>().error("操作失败");
        }
        return cartService.addCart(BaseContextHandler.getUserId(),goodsId,goodsAttribute,number);

    }

    /**
     * 展示购物车
     * */
    @GetMapping("/getCartList")
    @ApiOperation(value = "获取购物车列表")
    public ObjectRestResponse<List<GoodsVo>> getCartList(){
        return cartService.getCartList(BaseContextHandler.getUserId());
    }

    /**
     * 更新购物车
     * */
    @ApiOperation(value = "更新购物车")
    @PostMapping("/updateCart")
    public ObjectRestResponse<Object> updateCart(String goodsId,String goodsAttribute,Integer number){
        if (goodsId == null || goodsAttribute == null || number == null){
            return new ObjectRestResponse<>().error("操作失败");
        }
        return cartService.updateCart(BaseContextHandler.getUserId(),goodsId,goodsAttribute,number);
    }

    @ApiOperation(value = "删除购物车中一个商品")
    @PostMapping("/delCartGoods")
    public ObjectRestResponse<Object> delCartGoods(String goodsId,String goodsAttribute){
        if (goodsId == null || goodsAttribute == null){
            return new ObjectRestResponse<>().error("操作失败");
        }
        return cartService.delCartGoods(BaseContextHandler.getUserId(),goodsId,goodsAttribute);
    }

    @ApiOperation(value = "清空购物车")
    @GetMapping("/delCart")
    public ObjectRestResponse<Object> delCart(){
        return cartService.delCart(BaseContextHandler.getUserId());
    }

    @ApiOperation(value = "勾选其中一个")
    @PostMapping("/check")
    public ObjectRestResponse<Object> check(String goodsId,String goodsAttribute,String check){
        if (goodsId == null || goodsAttribute == null || check == null){
            return new ObjectRestResponse<>().error("操作失败");
        }
        return cartService.check(BaseContextHandler.getUserId(),goodsId,goodsAttribute,check);
    }

    @ApiOperation(value = "勾选中的商品去结算")
    @PostMapping("/toConfirm")
    public ObjectRestResponse<Map<String ,Object>> toConfirm(){
        return cartService.toConfirm(BaseContextHandler.getUserId());
    }
}
