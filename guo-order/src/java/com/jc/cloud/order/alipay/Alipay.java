package com.jc.cloud.order.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Component;
/**
 * 支付宝支付接口
 * @author lgh
 * @date May 25 , 2019
* */
@Component
public class Alipay {
    /**
    *  支付接口
     * @param bean
     * @return
     * @throws AlipayApiException
    * */
    public String pay(AlipayBean bean) throws AlipayApiException {
        //获取初始化的AlipayClient
        String serverUrl = AlipayProperties.getGatewayUrl();
        String appId = AlipayProperties.getAppId();
        String privateKey = AlipayProperties.getPrivaryKey();
        String format = "json";
        String charset = AlipayProperties.getCharset();
        String alipayPublicKey = AlipayProperties.getPublicKey();
        String signType = AlipayProperties.getSignType();
        String returnUrl = AlipayProperties.getReturnUrl();
        String notifyUrl = AlipayProperties.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,appId,privateKey,format,charset,alipayPublicKey,signType);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        //服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        //封装参数
        alipayRequest.setBizContent(JSON.toJSONString(bean));
        //请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //返回付款结果
        return result;
    }


}
