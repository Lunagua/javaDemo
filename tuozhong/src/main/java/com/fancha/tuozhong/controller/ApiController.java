package com.fancha.tuozhong.controller;

import com.alibaba.fastjson.JSONObject;
import com.fancha.tuozhong.entity.Data;
import com.fancha.tuozhong.entity.Info;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import com.fancha.tuozhong.util.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping(value = "/test")
    public Data get(Info info) {
        return dealWith(info);
    }

    @PostMapping(value = "/tuozhong")
    public Data post(@RequestBody Info info) {
        return dealWith(info);
    }

    /**
     * @param info 用户的信息
     * @return 处理用户信息的结果
     */
    private Data dealWith(Info info) {
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMFWVvyACjTbzto5EmIPtKRq6req/U1P9i/evTl9rsTfr9UOhyppgeWbwHY4ET/IXeNYKVA3bn9zrAQ0daXGxj7uz4C49lGNyjqmeBpxg9c/sjPAWTGA4J8q4lMPaEDcl6sqeY8F8cAsdAc665OHgkUzaz+PnUXeW56lWNUK24wfAgMBAAECgYBbHLpPsnOc5327H6xHJEmoNm648c7/sVA+zQR15IIJa7UsffU7Co/ZyiXQUKnoWh/M9Q47LHRZggSt0p8i6eMgka0De7shNb2B5GnixgnCFnBOSCjhQ7Q0KiOzu1Z2v+0ieR1w4scLjPCVTEr6LDJuZmJnlWFFbUu4eDqtk38foQJBAODvCSb95MYexwnKQAuLdZ2UXdJuchuaQXoBY+7chPeaYNktoCE+kg+JMmo6HMnrRWmR9ItAFjBifimKJTGDH/ECQQDcCiZzH2Y/3CBmuqQmDx/1mSRyB/ITrQhFmP8drSD9auG17osJRU8m/LM0gTxT74TM80/Xybi43Qm9UihK1H0PAkBu5mvkxZld9R69PPKGvqOmpdf8QGgSI+PUe4sB4cfNLFhDu7MPsQHilwo8RZqcE1uNyWCAp4BJKsZ5RJtB5hyBAkAmjByqRFDsVQrUFFRXWoT1yItK8rtk2QY+8wuWTlMeMhAyiASRbjPsl45pX06LQcWZBfLx0aPrtsVTBkaKvWjfAkEA3GvI33icY4jkxTyplA7ZQkcehDFQQFgxBExV6CrqIE1ZE2mIc2/HgeppgPKsJqyYNkDkZ1QcoyAGNmv2qwPKKQ==";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCU9sMorCPPlSpAxE3mEg46WI+0d8zA26MtApzDZjAZeMqchZ4TuiDm18XC1oWAbOCvmA5e6PjvshSSr1vOmPmVYC+MUztSAbgaXfmDx+i4M5znSM0d3anQpFN/ulgyHBxtt2QwnCXrOBdYg233N37iLuCgoRZHLRjetlWubscdVwIDAQAB";
        String reqData = info.getData();
        String reqKey = info.getKey();
        String merchantNo = info.getMerchantId();
        String keyRes = null;
        try {
            keyRes = RSAUtils.decryptByPublicKey(reqKey, publicKey);
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

        String dataDecrypt = null;
        try {
            dataDecrypt = AESUtils.decrypt(reqData, keyRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dataDecrypt);
        JSONObject res = (JSONObject) JSON.parse(dataDecrypt);
        String merchantOrderNo = res.getString("merchantOrderNo");

        String aesKey = "0123456789012345";
        Data result = new Data();
        Map<String, String> params = new HashMap<>();
        params.put("merchantOrderNo", merchantOrderNo);
        params.put("code", "0");
        String dataEncrypt = "";
        try {
            dataEncrypt = AESUtils.encrypt(JSON.toJSONString(params), aesKey);
            System.out.println(aesKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String keyEncrypt = "";
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
        result.setVersion("1.0.0");
        result.setMerchantId(merchantNo);
        result.setData(dataEncrypt);
        result.setKey(keyEncrypt);
        return result;
    }
}