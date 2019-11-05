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

    public static void main(String[] args) throws Exception {
        String data = getDataEncrypt();
        System.out.println(data);
//        String key = getKeyEncrypt(Constant.PRIVATE_KEY);
//        System.out.println(key);
        String resData = "FHI/pHVvPzX7Hytz9UqT7FPbfJkjB52QMLYxrJbE2kxlJo7YL8/+T5+mPJjM0zHLwPBUAqOtEbBFiv2LYml/myv8hIHA2HU/AAfBuwhR9oohPSFag0IbKXL0j44xUNi+";
        String resKey = "MoM2DjZ0PJ7dI4usbBMV+OtM5J4aoqIdgJfk9F/aAatEpwR8htxODYs+HWDX5K9hnr0SSIbfWpKES98ZaI9KZzk6hG4ZaQceRjEHVrcThruKZxR7yihpyZ+Fs9Ky+44zAzXofmpMdJEV2WLyuhw8NkYhdQ1Oom4UuQskg1SxcIc=";


//        getDataDecrypt(resData,resKey,Constant.PUBLIC_KEY);


    }
}

