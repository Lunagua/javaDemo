package com.tz.demo.JmeterTools;

import com.alibaba.fastjson.JSONObject;
import com.tz.demo.constant.Constant;
import com.tz.demo.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

public class BaseData {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static String getAmount() {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format((Math.random() + 10 + new Random().nextInt(1000)));
    }


    public static String generateKey() {
        String AESKey = "";
        int number;
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < 16; i++) {
            number = Math.abs(random.nextInt()) % 10;
            AESKey += number;
        }
        return AESKey;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(getAmount());
        }

    }

    /**
     * 参数转换
     *
     * @param data 参数
     * @return 转换结果
     */
    public static Map<String, String> convert(Map<String, String> data, String merchantNo, String privateKey) throws Exception {
        Map<String, String> result = new TreeMap<>(data);

        result.put("merchantNo", merchantNo);
        result.put("version", "1.0.0");
        String signData = result.entrySet().stream().filter(e -> StringUtils.isNotEmpty(e.getValue())).map(e -> e.getKey().concat("=").concat(e.getValue())).collect(Collectors.joining("&"));
        result.put("sign", RSAUtils.sign(signData, "UTF-8", privateKey, RSAUtils.MD5_WITH_RSA));
        return result;
    }

    /**
     * 参数还原
     *
     * @param data 参数
     * @return 还原结果
     */
    public static JSONObject revert(JSONObject data) throws Exception {

        Map<String, Object> map = new TreeMap<>(data);
        String sign = map.remove("sign").toString();
        String signData = map.entrySet().stream().filter(e -> StringUtils.isNotEmpty(e.getValue().toString())).map(e -> e.getKey().concat("=").concat(e.getValue().toString())).collect(Collectors.joining("&"));
        if (!RSAUtils.verify(signData, "UTF-8", Constant.PUBLIC_KEY, sign, "UTF-8", RSAUtils.MD5_WITH_RSA)) {
            throw new RuntimeException("验签失败");
        }
        return data;
    }

}
