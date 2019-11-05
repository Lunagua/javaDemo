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
public class CommonPayTest extends BaseTest {

    /**
     * 创建订单
     */
    @Test
    public void create() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("merchantOrderNo", String.valueOf(System.currentTimeMillis()));
            params.put("amount", "300");
            params.put("model", "UPOP");
            params.put("notifyUrl", "http://www.baidu.com");
            System.out.println(JSON.toJSONString(params));
            params = convert(params);
            System.out.println(JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.UPOP_CREATE_URL, JSON.toJSONString(params));
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
            params.put("merchantOrderNo", "1565585721516");
            params.put("submitTime","20190812130000");
            System.out.println(JSON.toJSONString(params));
            params = convert(params);
            System.out.println(JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.UPOP_QUERY_URL, JSON.toJSONString(params));
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
