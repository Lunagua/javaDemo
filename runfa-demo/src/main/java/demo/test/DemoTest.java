package com.tz.demo.test;

import com.alibaba.fastjson.JSONObject;
import com.tz.demo.service.DemoService;
import com.tz.demo.util.RSAUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;


public class DemoTest {

    private DemoService demoService = new DemoService();

    @Test
    public void createPayOrder() {
        JSONObject jsonObject = demoService.createPayOrder("20191108183721", "ebank", "100", "100", "http://www.baidu.com");
        System.out.println("加密前数据：".concat(jsonObject.getString("paramsData")));
        System.out.println("加密后数据：".concat(jsonObject.getString("params")));
        System.out.println("返回密文数据：".concat(jsonObject.getString("result")));
        System.out.println("返回明文数据：".concat(jsonObject.getString("resultData")));
    }

    @Test
    public void queryPayOrder() {
        JSONObject jsonObject = demoService.queryPayOrder("20191108183711", new Date());
        System.out.println("加密前数据：".concat(jsonObject.getString("paramsData")));
        System.out.println("加密后数据：".concat(jsonObject.getString("params")));
        System.out.println("返回密文数据：".concat(jsonObject.getString("result")));
        System.out.println("返回明文数据：".concat(jsonObject.getString("resultData")));
    }

    @Test
    public void createRemitOrder() {
        JSONObject jsonObject = demoService.createRemitOrder("20191012144902", "ICBC", "100", "123456789123456789", "电风扇", "http://www.baidu.com");
        System.out.println("加密前数据：".concat(jsonObject.getString("paramsData")));
        System.out.println("加密后数据：".concat(jsonObject.getString("params")));
        System.out.println("返回密文数据：".concat(jsonObject.getString("result")));
        System.out.println("返回明文数据：".concat(jsonObject.getString("resultData")));
    }

    @Test
    public void queryRemitOrder() {
        JSONObject jsonObject = demoService.queryRemitOrder("20191012144902", new Date());
        System.out.println("加密前数据：".concat(jsonObject.getString("paramsData")));
        System.out.println("加密后数据：".concat(jsonObject.getString("params")));
        System.out.println("返回密文数据：".concat(jsonObject.getString("result")));
        System.out.println("返回明文数据：".concat(jsonObject.getString("resultData")));
    }

    @Test
    public void queryBalance() {
        JSONObject jsonObject = demoService.queryBalance();
        System.out.println("加密前数据：".concat(jsonObject.getString("paramsData")));
        System.out.println("加密后数据：".concat(jsonObject.getString("params")));
        System.out.println("返回密文数据：".concat(jsonObject.getString("result")));
        System.out.println("返回明文数据：".concat(jsonObject.getString("resultData")));
    }

    public static void main(String[] args) {
        String key = "CHNB3qu/mA53h5OQeQYWtKmcBWNVu/+10u3j/ycLCJxme3nIySputcAwoc24t9vo/H/VtpEKS3bK3QuXN0Fa3H20/hQQ/ENKhV7qdAp7rOunYXL1HlQAm9KDVsrIN3XscUFamR9eYIomtHyiBwWI1TVDogBmgjW+WYB6ZZyGZwQ=";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXIGuplLCnRUCWFihbueDkDTLL+7JonrCkarPsLw5/7vOr2V1K/4HnyEp79OaaDg2zqxIiJF7edvc9VlvXiuD/S4NXSXYIKvGWGhviaqcvatb2WZAHi+8S+/+HdnoB1OQzGT/FcYPu6N7CVnnXeIwPAeP+Khje8qAVTdKnsv5ZgQIDAQAB";

        try {
            System.out.println(RSAUtils.decryptByPublicKey(key, publicKey));
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
    }

}
