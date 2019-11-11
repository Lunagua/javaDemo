package com.tz.demo.JmeterTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PayOrder {
    public static JSONObject createPayOrder(String merchantOrderNo, String model, String amount, String memberNo, String bankCode, String notifyUrl, String merchantNo) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("amount", amount);
            params.put("payType", model);
            params.put("bankCode", bankCode);
            params.put("memberNo", memberNo);
            params.put("notifyUrl", notifyUrl);
            params = BaseData.convert(params, merchantNo);
            System.out.println("订单参数：" + params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(JSON.toJSONString(params));
    }

    public static JSONObject createPayOrder(String amount, String merchantNo) {
        String merchantOrderNo = BaseData.getUUID();
        String model = "ebank";
        String notifyUrl = "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback";
        String bankCode = "ICBC";
        String memberNo = BaseData.getAmount();
        return PayOrder.createPayOrder(merchantOrderNo, model, amount, memberNo, bankCode, notifyUrl, merchantNo);
    }

    public static JSONObject createPayOrder(String merchantNo) {
        String amount = BaseData.getAmount();
        return PayOrder.createPayOrder(amount, merchantNo);
    }

    public static void main(String[] args) {
        PayOrder.createPayOrder("21910072");
    }
}
