package com.tz.demo.JmeterTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tz.demo.constant.Constant;
import com.tz.demo.util.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PayOrder {
    public static JSONObject createPayOrder(String merchantOrderNo, String model, String amount, String memberNo, String bankCode, String notifyUrl, String merchantNo,String privateKey) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("amount", amount);
            params.put("payType", model);
            params.put("bankCode", bankCode);
            params.put("memberNo", memberNo);
            params.put("notifyUrl", notifyUrl);
            params = BaseData.convert(params, merchantNo,privateKey);
            System.out.println("订单参数：" + params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(JSON.toJSONString(params));
    }

    public static JSONObject createPayOrder(String merchantNo, String amount,String privateKey) {
        String merchantOrderNo = BaseData.getUUID();
        String model = "ebank";
        String notifyUrl = "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback";
        String bankCode = "ICBC";
        String memberNo = BaseData.generateKey();
        return PayOrder.createPayOrder(merchantOrderNo, model, amount, memberNo, bankCode, notifyUrl, merchantNo,privateKey);
    }

    public static JSONObject createPayOrder(String merchantNo,String privateKey) {
        String amount = BaseData.getAmount();
        return PayOrder.createPayOrder(merchantNo, amount,privateKey);
    }

    public static JSONObject queryPayOrder(String merchantOrderNo, String merchantNo,String privateKey) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("submitTime", DateUtils.format(new Date(), "yyyyMMddHHmmss"));
            params = BaseData.convert(params, merchantNo, privateKey);
            System.out.println("查询支付订单参数为：" + params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(JSON.toJSONString(params));
    }

    public static void main(String[] args) {
        PayOrder.createPayOrder("21910072", Constant.PRIVATE_KEY);
        PayOrder.queryPayOrder("1111", "2222",Constant.PRIVATE_KEY);
    }
}
