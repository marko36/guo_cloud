package com.jc.cloud.order.service.impl;

import com.alipay.api.AlipayApiException;
import com.jc.cloud.order.alipay.Alipay;
import com.jc.cloud.order.alipay.AlipayBean;
import com.jc.cloud.order.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }
}
