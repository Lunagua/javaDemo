package com.tz.demo.JmeterTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tz.demo.constant.Constant;
import com.tz.demo.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CreatePayOrder {

    public static JSONObject createPayOrder(String merchantOrderNo, String model, String amount, String memberNo, String notifyUrl) {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String> params = new HashMap<>();
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("amount", amount);
            params.put("payType", model);
            params.put("bankCode", "ICBC");
            params.put("memberNo",memberNo);
            params.put("notifyUrl",notifyUrl);
            jsonObject.put("paramsData", JSON.toJSONString(params));
            params = convert(params);
            jsonObject.put("params", JSON.toJSONString(params));

//            String result = HttpUtils.post(Constant.PAY_CREATE_URL, JSON.toJSONString(params));
//            jsonObject.put("result", result);
//            JSONObject data = JSONObject.parseObject(result);
//            if (data.getInteger("code") == 0) {
//                data = revert(data);
//                jsonObject.put("resultData", data.toJSONString());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    /**
     * 参数转换
     * @param data 参数
     * @return 转换结果
     */
    private static Map<String, String> convert(Map<String, String> data) throws Exception {
        Map<String, String> result = new TreeMap<>(data);

        result.put("merchantNo", Constant.MERCHANT_ID);
        result.put("version", "1.0.0");
        String signData = result.entrySet().stream().filter(e -> StringUtils.isNotEmpty(e.getValue())).map(e -> e.getKey().concat("=").concat(e.getValue())).collect(Collectors.joining("&"));
        result.put("sign", RSAUtils.sign(signData, "UTF-8", Constant.PRIVATE_KEY, RSAUtils.MD5_WITH_RSA));

        return result;
    }

    /**
     * 参数还原
     * @param data 参数
     * @return 还原结果
     */
    private static JSONObject revert(JSONObject data) throws Exception {

        Map<String, Object> map = new TreeMap<>(data);
        String sign = map.remove("sign").toString();
        String signData = map.entrySet().stream().filter(e -> StringUtils.isNotEmpty(e.getValue().toString())).map(e -> e.getKey().concat("=").concat(e.getValue().toString())).collect(Collectors.joining("&"));
        if (!RSAUtils.verify(signData, "UTF-8", Constant.PUBLIC_KEY, sign, "UTF-8", RSAUtils.MD5_WITH_RSA)) {
            throw new RuntimeException("验签失败");
        }
        return data;
    }

    public static void main(String[] args) {

        JSONObject jsonObject = CreatePayOrder.createPayOrder("20191108183731", "ebank", "100", "100", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
        System.out.println("加密前数据：".concat(jsonObject.getString("paramsData")));
        System.out.println("加密后数据：".concat(jsonObject.getString("params")));
//        System.out.println("返回密文数据：".concat(jsonObject.getString("result")));
//        System.out.println("返回明文数据：".concat(jsonObject.getString("resultData")));

    }
}
