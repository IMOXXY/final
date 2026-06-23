package com.his.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.his.config.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderAlipay {

    private final AlipayClient alipayClient;
    private final AlipayProperties properties;

    @Autowired
    public OrderAlipay(AlipayProperties properties) {
        this.properties = properties;
        this.alipayClient = new DefaultAlipayClient(
                properties.getGatewayUrl(),
                properties.getAppId(),
                properties.getPrivateKey(),
                "json",
                "UTF-8",
                properties.getPublicKey(),
                "RSA2"
        );
    }

    public String createQrCode(String orderNo, double totalAmount, String subject) throws Exception {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setNotifyUrl(properties.getNotifyUrl());

        String bizContent = "{" +
                "\"out_trade_no\":\"" + orderNo + "\"," +
                "\"total_amount\":\"" + totalAmount + "\"," +
                "\"subject\":\"" + subject + "\"," +
                "\"timeout_express\":\"15m\"" +
                "}";

        request.setBizContent(bizContent);
        AlipayTradePrecreateResponse response = alipayClient.execute(request);

        if (response.isSuccess()) {
            return response.getQrCode();
        } else {
            throw new RuntimeException("支付宝扫码下单失败：" + response.getSubMsg());
        }
    }
}
