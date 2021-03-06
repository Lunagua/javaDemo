package com.tz.demo.JmeterTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tz.demo.constant.Constant;
import com.tz.demo.util.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RemitOrder {
    public static JSONObject createRemitOrder(String merchantOrderNo, String bankCode, String amount, String bankcardAccountNo, String bankcardAccountName, String notifyUrl, String merchantNo,String privateKey) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("amount", amount);
            params.put("bankCode", bankCode);
            params.put("bankcardAccountNo", bankcardAccountNo);
            params.put("bankcardAccountName", bankcardAccountName);
            params.put("notifyUrl", notifyUrl);
            params = BaseData.convert(params, merchantNo, privateKey);
            System.out.println("代付订单参数为：" + params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(JSON.toJSONString(params));
    }

    public static JSONObject createRemitOrder(String merchantNo, String amount,String privateKey) {
        String merchantOrderNo = BaseData.getUUID();
        String bankcardAccountNo = "621558180300381" + new Random().nextInt(1000000);
        String notifyUrl = "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback";
        String bankcardAccountName = "袁达";
        String bankCode = "ICBC";
        return RemitOrder.createRemitOrder(merchantOrderNo, bankCode, amount, bankcardAccountNo, bankcardAccountName, notifyUrl, merchantNo, privateKey);
    }

    public static JSONObject createRemitOrder(String merchantNo,String privateKey) {
        String amount = BaseData.getAmount();
        return RemitOrder.createRemitOrder(merchantNo, amount, privateKey);
    }

    public static JSONObject queryRemitOrder(String merchantOrderNo, String merchantNo,String privateKey) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("submitTime", DateUtils.format(new Date(), "yyyyMMddHHmmss"));
            params = BaseData.convert(params, merchantNo, privateKey);
            System.out.println("查询代付订单参数为：" + params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(JSON.toJSONString(params));
    }

    public static void main(String[] args) {
        RemitOrder.createRemitOrder("21910072", Constant.PRIVATE_KEY);
        RemitOrder.queryRemitOrder("21910072", "20191111130725",Constant.PRIVATE_KEY);
    }
}
