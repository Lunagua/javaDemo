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

public class CreateWechatWap {
    public static String aesKey = AESUtils.generateKey();

    public static String getDataEncrypt(String amount, String memberNo) {
        Map<String, String> params = new HashMap<>();
        params.put("merchantOrderNo", String.valueOf(System.currentTimeMillis()));
        params.put("amount", amount);
        params.put("model", "WECHAT_WAP");
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

    public static void main(String[] args) throws Exception {
//        String data = getDataEncrypt("10","0001");
//        System.out.println(data);
//        String key = getKeyEncrypt(Constant.PRIVATE_KEY);
//        System.out.println(key);

//        String orderno = "http://apitest.henglipay.com/upop-pay?orderNo=201908101219341000460";
//        System.out.println(orderno.split("=")[1]);
//
//
//        String js = "{\"amount\":\"10\",\"merchantId\":\"21910072\",\"merchantOrderNo\":\"1565405432255\",\"status\":0}";
//        JSONObject oo = jsonMethod(js);
//        System.out.println(oo.getString("merchantOrderNo"));
//        String resData = "UkxTpWB1sY7DXj9urYR2ToTtESyCnW9+zh4h+hdbGrrpA6i6niwny1IFOvwurZmx8cZhcIYzBLqYbs/eeABAYTcRHyTH1/NM8ekxUG2f70pgYuhyjD/UhtltAdCuw+dejjyNta0WUs4HRAO1JDyPrq4zhwWIcgrxGnpMJ9PmljJqYWjss8Bsyvekfv3lXUFGgpz2hU1Bjq52OO5rdfhjYA==";
//        String resKey = "l/4QVCYm914K7MDPFFtRPN7di3OKFzYsbNNctzGYZemxLB9zrnafDhfb3ac3i2PS1fYRQoEUfJMlSCOOK5CGvt2NiTk2v62CT4olzVfRPwCtvgY2kaunZtGL1amIpDFhGJXrJ1yZBkLblvI38xAW2uRRbf74AUK2yuOawT12sy4=";
////        String key1 = getKeyDecrypt(resKey,Constant.PUBLIC_KEY);
////        System.out.println(key1);
//
//        getDataDecrypt(resData,resKey,Constant.PUBLIC_KEY);

        String data = getDataEncrypt("222","222");
        System.out.println(data);
        String privatekey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMFWVvyACjTbzto5EmIPtKRq6req/U1P9i/evTl9rsTfr9UOhyppgeWbwHY4ET/IXeNYKVA3bn9zrAQ0daXGxj7uz4C49lGNyjqmeBpxg9c/sjPAWTGA4J8q4lMPaEDcl6sqeY8F8cAsdAc665OHgkUzaz+PnUXeW56lWNUK24wfAgMBAAECgYBbHLpPsnOc5327H6xHJEmoNm648c7/sVA+zQR15IIJa7UsffU7Co/ZyiXQUKnoWh/M9Q47LHRZggSt0p8i6eMgka0De7shNb2B5GnixgnCFnBOSCjhQ7Q0KiOzu1Z2v+0ieR1w4scLjPCVTEr6LDJuZmJnlWFFbUu4eDqtk38foQJBAODvCSb95MYexwnKQAuLdZ2UXdJuchuaQXoBY+7chPeaYNktoCE+kg+JMmo6HMnrRWmR9ItAFjBifimKJTGDH/ECQQDcCiZzH2Y/3CBmuqQmDx/1mSRyB/ITrQhFmP8drSD9auG17osJRU8m/LM0gTxT74TM80/Xybi43Qm9UihK1H0PAkBu5mvkxZld9R69PPKGvqOmpdf8QGgSI+PUe4sB4cfNLFhDu7MPsQHilwo8RZqcE1uNyWCAp4BJKsZ5RJtB5hyBAkAmjByqRFDsVQrUFFRXWoT1yItK8rtk2QY+8wuWTlMeMhAyiASRbjPsl45pX06LQcWZBfLx0aPrtsVTBkaKvWjfAkEA3GvI33icY4jkxTyplA7ZQkcehDFQQFgxBExV6CrqIE1ZE2mIc2/HgeppgPKsJqyYNkDkZ1QcoyAGNmv2qwPKKQ==";

        String key = RSAUtils.encryptByPrivateKey("0372905562757482",privatekey);
        System.out.println(key);

    }
}
