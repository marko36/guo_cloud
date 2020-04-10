package com.jc.cloud.order.api;

import com.alipay.api.AlipayApiException;
import com.jc.cloud.order.alipay.AlipayBean;
import com.jc.cloud.order.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    PayService payService;

    /**
     * 阿里支付
     * @Param tradeNo
     * @Param subject
     * @Param amount
     * @Param body
     * @return
     * @throws AlipayApiException
    * */
    @PostMapping(value = "/alipay")
    public String alipay(String outTradeNo,String subject,String totalAmount,String body) throws AlipayApiException{
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOut_trade_no(outTradeNo);
        alipayBean.setSubject(subject);
        alipayBean.setTotal_amount(totalAmount);
        alipayBean.setBody(body);
        return payService.aliPay(alipayBean);
    }
}
