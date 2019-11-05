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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QueryAccount {
    public static String aesKey = AESUtils.generateKey();

    public static String queryBalance() {
        Map<String, String> params = new HashMap<>();
        params.put("requestTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
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
        String data = queryBalance();
        System.out.println(data);
        String key = getKeyEncrypt(Constant.PRIVATE_KEY);
        System.out.println(key);


        String resData = "FobBtRHnU7pYgQgxkf0hqk6PJlo0imRGg09qgcYnKftjNOOyRsq1+Cn0SnooMNEdLyJxhgPxIbRzafII7VUtrikaRFAcArm9PhwfRnHNYxw=";
        String resKey = "DfMnQUqCh6lxFnxZ/Em6SxiiPFFaTg4JWC4WWG6BZznLWhWnhvld3vaJf4JeOF8DABH8HkbRXLSg8tE7ZHRqg8BLrTravjZd8SCQID9xAlmcv8ofsDYfguIw7Dt3pYXRPpHdmuDvz8c5YK4awjCsRsjudTmcKUWgdqGEcWj5l0A=";


        getDataDecrypt(resData, resKey, Constant.PUBLIC_KEY);
    }

}
