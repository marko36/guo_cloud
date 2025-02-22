package com.jc.cloud.order.alipay;

import lombok.Data;

/**
 * 支付实体对象
 * 根据支付宝接口协议，其中的属性名必须使用下划线，不能修改
 * @author lgh
 * @date May 25 , 2019
* */
@Data
public class AlipayBean {
    /**
     * 商品订单号，必填
    * */
    private String out_trade_no;
    /**
     * 订单名称，必填
    * */
    private String subject;
    /**
     * 付款金额，必填
     * 根据支付宝接口协议，必须使用下划线
     * */
    private String total_amount;
    /**
     * 商品描述，可空
    * */
    private String body;
    /**
     * 超时时间参数
    * */
    private String timeout_express = "10m";
    /**
     * 商品编号
    * */
    private String product_code = "FAST_INSTANT_TRADE_PAY";

}
