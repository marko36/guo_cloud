package com.jc.cloud.order.service;

import com.alipay.api.AlipayApiException;
import com.jc.cloud.order.alipay.AlipayBean;

/**
 * 支付服务
 * @author lgh
 * @date May 25 , 2019
* */
public interface PayService {
    /**
    * 支付宝支付接口
     * @param alipayBean
     * @return
     * @throws AlipayApiException
    * */
    String aliPay(AlipayBean alipayBean) throws AlipayApiException;
}
