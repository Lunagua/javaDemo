package com.xiaoxingxing.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxingxing.demo.constant.Constant;
import com.xiaoxingxing.demo.util.HttpUtils;
import org.junit.Test;

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
            params.put("notifyUrl", "http://www.baidu.com");
            System.out.println(JSON.toJSONString(params));
            params = convert(params);
            System.out.println(JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.REMIT_CREATE_URL, JSON.toJSONString(params));
            System.out.println(result);
            JSONObject data = JSONObject.parseObject(result);
            if (data.getInteger("code") == 0) {
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
            params.put("merchantOrderNo", "1565587432024");
            params.put("submitTime","20190812132500");
            System.out.println(JSON.toJSONString(params));
            params = convert(params);
            System.out.println(JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.REMIT_QUERY_URL, JSON.toJSONString(params));
            System.out.println(result);
            JSONObject data = JSONObject.parseObject(result);
            if (data.getInteger("code") == 0) {
                data = revert(data);
                System.out.println(data.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
