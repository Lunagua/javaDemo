package com.xiaoxingxing.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxingxing.demo.constant.Constant;
import com.xiaoxingxing.demo.util.HttpUtils;
import com.xiaoxingxing.demo.util.RSAUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * 代付至银行卡
 */
public class RemitTest extends BaseTest {

    /**
     * 创建订单
     */
    @Test
    public void create() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("merchantOrderNo", String.valueOf(System.currentTimeMillis()));
            params.put("amount", "10");
            params.put("bankCode", "ICBC");
            params.put("bankcardAccountNo", "6215581803003818756");
            params.put("bankcardAccountName", "袁达");
            params.put("notifyUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
            params = convert(params);
            String result = HttpUtils.post(Constant.REMIT_CREATE_URL, JSON.toJSONString(params));
            JSONObject data = JSONObject.parseObject(result);
            System.out.println(data);
            if (data.getInteger("code") == 200) {
                data = revert(data);
                System.out.println(data.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单查询
     */
    @Test
    public void query() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("merchantOrderNo", "1565331087676");
            params.put("submitTime", "20190809140800");
            System.out.println(params);
            params = convert(params);

            String result = HttpUtils.post(Constant.REMIT_QUERY_URL, JSON.toJSONString(params));
            JSONObject data = JSONObject.parseObject(result);
            if (data.getInteger("code") == 200) {
                data = revert(data);
                System.out.println(data.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
