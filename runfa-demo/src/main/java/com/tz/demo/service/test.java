package com.tz.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.tz.demo.constant.Constant;
import com.tz.demo.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class test {
    private static JSONObject revert(JSONObject data,String publickey) throws Exception {

        Map<String, Object> map = new TreeMap<>(data);
        String sign = map.remove("sign").toString();
        String signData = map.entrySet().stream().filter(e -> StringUtils.isNotEmpty(e.getValue().toString())).map(e -> e.getKey().concat("=").concat(e.getValue().toString())).collect(Collectors.joining("&"));
        if (!RSAUtils.verify(signData, "UTF-8", Constant.PUBLIC_KEY, sign, "UTF-8", RSAUtils.MD5_WITH_RSA)) {
            throw new RuntimeException("验签失败");
        }
        return data;
    }
    public static void main(String[] args) throws Exception {
        String data = "{\"amount\": \"428.84\", \"merchantNo\": \"21910323\", \"merchantOrderNo\": \"f5af5117c8ee4456a46a59dc5a46e55c\", \"bankcardAccountNo\": \"621558180300381911123\", \"bankcardAccountName\": \"\\u8881\\u8fbe\", \"sign\": \"cWiKO4Ow2zc8ztoXk3Z9FQcc03Ylf3aTwpd3Y2Vwa5vf6eOHZrU/E8YXJdrNt8jS5Im2YPmxh0yDVP/f4trQKx5UKNhfbkWsBSrfts1AcPSAVKDMBtSHqjhDX61A3yLt9DfqivUoxmmNl/xpPb1mncHNwfpi0oLjuA/vZO9Mayw=\"}";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDe/iOx4XtOMwsPq83LHLc6cHmxCcJwA82B7ptrEQeyIc6XNK+9ZjPeXKntoj/FaiGQyd1o9cye8NyEnpTulRA5DZFs/r8ua8bFUVjCaFKcPQb9+qZBDwDD9LuAr5g6MPD/vvY+FPQ/4TzZQAcSIhzew45QLStoNq/WhSnl7/X+vwIDAQAB";

        JSONObject data1 = JSONObject.parseObject(data);
        System.out.println(data1);

        Map<String, Object> map = new TreeMap<>(data1);
        System.out.println(map);

        String sign = map.remove("sign").toString();
        System.out.println(sign);
        System.out.println(map);

        String signData = map.entrySet().stream().filter(e -> StringUtils.isNotEmpty(e.getValue().toString())).map(e -> e.getKey().concat("=").concat(e.getValue().toString())).collect(Collectors.joining("&"));
        System.out.println(signData);

        System.out.print(RSAUtils.verify(signData,"UTF-8",publicKey,sign,"UTF-8",RSAUtils.MD5_WITH_RSA));
    }
}
