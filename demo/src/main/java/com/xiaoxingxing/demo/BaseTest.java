package com.xiaoxingxing.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxingxing.demo.constant.Constant;
import com.xiaoxingxing.demo.util.AESUtils;
import com.xiaoxingxing.demo.util.RSAUtils;

import java.util.Map;
import java.util.TreeMap;

public abstract class BaseTest {

    protected Map<String, String> convert(Object data) throws Exception {
        Map<String, String> result = new TreeMap<>();

        String aesKey = AESUtils.generateKey();
        System.out.println(aesKey);
        result.put("merchantId", Constant.MERCHANT_ID);
        result.put("version", "1.0.0");
        String dataRes = AESUtils.encrypt(JSON.toJSONString(data), aesKey);
        String keyRes = RSAUtils.encryptByPrivateKey(aesKey, Constant.PRIVATE_KEY);
        result.put("data", dataRes);
        result.put("key", keyRes);
        System.out.println(result);

        return result;
    }

    protected JSONObject revert(JSONObject data) throws Exception {
        String aesKey = data.getString("key");
        aesKey = RSAUtils.decryptByPublicKey(aesKey, Constant.PUBLIC_KEY);
        return JSONObject.parseObject(AESUtils.decrypt(data.getString("data"), aesKey));
    }


}
