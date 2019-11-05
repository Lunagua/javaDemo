package com.xiaoxingxing.demo.jmeterTools;

import com.alibaba.fastjson.JSON;
import com.xiaoxingxing.demo.constant.Constant;
import com.xiaoxingxing.demo.util.AESUtils;
import com.xiaoxingxing.demo.util.RSAUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class QueryUPOPOrder {
    public static String aesKey = AESUtils.generateKey();

    public static String queryUPOPOrder(String merchantOrderNo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String submitTime = simpleDateFormat.format(Long.parseLong(merchantOrderNo));
        Map<String, String> params = new HashMap<>();
        params.put("merchantOrderNo", merchantOrderNo);
        params.put("submitTime", submitTime);
        String dataEncrypt = null;
        try {
            dataEncrypt = AESUtils.encrypt(JSON.toJSONString(params), aesKey);
            System.out.println(aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataEncrypt;
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


    public static void main(String[] args) {
        String data = queryUPOPOrder("1565330857842");
        System.out.println(data);
        String key = getKeyEncrypt(Constant.PRIVATE_KEY);
        System.out.println(key);


        String resData = "tR6o3PgT2lqf06/7OzsIbK3N76fW6xeW4huTKxKmGPZmcVYZDyQyZ9MP2JyIHjSQVrJaDDq+6FUx01u6GuaSIvni5Ecsx5YFLqPY0F5Auq5UIkgiRS7mtf4Bbv0Etddr";
        String resKey = "neoEoWT+QQhkMh3JxgwX1bO+b66qMmvs9otdz/KvpSpmAm6tVSrRHCgocCUDIwWdQaelqMOkSE4Z9/KuF1e2KM2tGHNPrxlIeysGCNS6nCQsPemyIsLV11QNJn/6tK7uh6Zcwjimb0RrZQF0R2DFh8WHntq7nQ9Deb5SWHilm2g=";


        getDataDecrypt(resData, resKey, Constant.PUBLIC_KEY);
    }
}
