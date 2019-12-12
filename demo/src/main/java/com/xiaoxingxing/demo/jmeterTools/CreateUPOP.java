package com.xiaoxingxing.demo.jmeterTools;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxingxing.demo.util.AESUtils;
import com.xiaoxingxing.demo.util.RSAUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CreateUPOP {
    public static String aesKey = AESUtils.generateKey();
    public static Map<String, String> params = new HashMap<>();

    public static String getDataEncrypt(String amount, String memberNo) {
        params.clear();
        params.put("merchantOrderNo", String.valueOf(System.currentTimeMillis()));
        params.put("amount", amount);
        params.put("model", "UPOP");
        params.put("notifyUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
        params.put("memberNo", memberNo);
        System.out.println(params);
        String dataEncrypt = null;
        try {
            dataEncrypt = AESUtils.encrypt(JSON.toJSONString(params), aesKey);
            System.out.println(aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataEncrypt;
    }

    public static String getDataEncrypt(String memberNo) {
        DecimalFormat df = new DecimalFormat("######0.00");
        String amount = df.format((int) (100 + Math.random() * 100));
        return getDataEncrypt(amount, memberNo);
    }

    public static String getDataEncrypt(int amount) {
        String memberNo = AESUtils.generateKey();
        return getDataEncrypt(amount + "", memberNo);
    }

    public static String getDataEncrypt() {
        String memberNo = AESUtils.generateKey();
        DecimalFormat df = new DecimalFormat("######0.00");
        String amount = df.format((int) (100 + Math.random() * 100));
        return getDataEncrypt(amount, memberNo);
    }

    public static JSONObject jsonMethod(String data) {
        return JSONObject.parseObject(data);
    }

    public static String getKeyEncrypt(String privateKey) {
        String keyEncrypt = null;
        try {
            keyEncrypt = RSAUtils.encryptByPrivateKey(aesKey, privateKey);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return keyEncrypt;
    }

    private static String getKeyDecrypt(String content, String publicKey) {
        String keyDecrypt = null;
        try {
            keyDecrypt = RSAUtils.decryptByPublicKey(content, publicKey);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return keyDecrypt;
    }

    public static String getDataDecrypt(String responseData, String responseKey, String publicKey) {

        String dataDecrypt = null;
        try {
            dataDecrypt = AESUtils.decrypt(responseData, getKeyDecrypt(responseKey, publicKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dataDecrypt);
        return dataDecrypt;
    }

    public static String getAmount(){
        return params.get("amount");
    }
}
