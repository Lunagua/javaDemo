package com.xiaoxingxing.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxingxing.demo.constant.Constant;
import com.xiaoxingxing.demo.util.HttpUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 银联在线支付
 */
public class UPOPTest extends BaseTest {

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
            params.put("notifyUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
            params.put("memberNo", "001");
            params = convert(params);

            String result = HttpUtils.post(Constant.UPOP_CREATE_URL, JSON.toJSONString(params));
            JSONObject data = JSONObject.parseObject(result);
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
            params.put("merchantOrderNo", "1565330857842");
            params.put("submitTime", "20190809140800");
            params = convert(params);

            String result = HttpUtils.post(Constant.UPOP_QUERY_URL, JSON.toJSONString(params));
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
