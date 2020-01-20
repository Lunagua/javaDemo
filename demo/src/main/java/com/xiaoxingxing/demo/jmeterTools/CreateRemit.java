package com.xiaoxingxing.demo.jmeterTools;

import com.alibaba.fastjson.JSON;
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
import java.util.Random;

public class CreateRemit {
    public static String aesKey = AESUtils.generateKey();

    public static String getDataEncrypt(String amount, String bankcardAccountNo) {
        Map<String, String> params = new HashMap<>();
        params.put("merchantOrderNo", String.valueOf(System.currentTimeMillis()));
//        params.put("merchantOrderNo", "1565676268585");
        params.put("amount", amount);
        params.put("bankCode", "ICBC");
        params.put("bankcardAccountNo", bankcardAccountNo);
        params.put("bankcardAccountName", "袁达");
        params.put("notifyUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
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

    public static String getDataEncrypt(String amount) {
        String bankcardAccountNo = "621558180300381" + new Random().nextInt(10000);
        return getDataEncrypt(amount, bankcardAccountNo);
    }

    public static String getDataEncrypt() {
        DecimalFormat df = new DecimalFormat("######0.00");
        String amount = df.format((int) (100 + Math.random() * 100));
        return getDataEncrypt(amount);
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
//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCU9sMorCPPlSpAxE3mEg46WI+0d8zA26MtApzDZjAZeMqchZ4TuiDm18XC1oWAbOCvmA5e6PjvshSSr1vOmPmVYC+MUztSAbgaXfmDx+i4M5znSM0d3anQpFN/ulgyHBxtt2QwnCXrOBdYg233N37iLuCgoRZHLRjetlWubscdVwIDAQAB";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMFWVvyACjTbzto5EmIPtKRq6req/U1P9i/evTl9rsTfr9UOhyppgeWbwHY4ET/IXeNYKVA3bn9zrAQ0daXGxj7uz4C49lGNyjqmeBpxg9c/sjPAWTGA4J8q4lMPaEDcl6sqeY8F8cAsdAc665OHgkUzaz+PnUXeW56lWNUK24wfAgMBAAECgYBbHLpPsnOc5327H6xHJEmoNm648c7/sVA+zQR15IIJa7UsffU7Co/ZyiXQUKnoWh/M9Q47LHRZggSt0p8i6eMgka0De7shNb2B5GnixgnCFnBOSCjhQ7Q0KiOzu1Z2v+0ieR1w4scLjPCVTEr6LDJuZmJnlWFFbUu4eDqtk38foQJBAODvCSb95MYexwnKQAuLdZ2UXdJuchuaQXoBY+7chPeaYNktoCE+kg+JMmo6HMnrRWmR9ItAFjBifimKJTGDH/ECQQDcCiZzH2Y/3CBmuqQmDx/1mSRyB/ITrQhFmP8drSD9auG17osJRU8m/LM0gTxT74TM80/Xybi43Qm9UihK1H0PAkBu5mvkxZld9R69PPKGvqOmpdf8QGgSI+PUe4sB4cfNLFhDu7MPsQHilwo8RZqcE1uNyWCAp4BJKsZ5RJtB5hyBAkAmjByqRFDsVQrUFFRXWoT1yItK8rtk2QY+8wuWTlMeMhAyiASRbjPsl45pX06LQcWZBfLx0aPrtsVTBkaKvWjfAkEA3GvI33icY4jkxTyplA7ZQkcehDFQQFgxBExV6CrqIE1ZE2mIc2/HgeppgPKsJqyYNkDkZ1QcoyAGNmv2qwPKKQ==";
        String merNo = "10002";



    }
}

