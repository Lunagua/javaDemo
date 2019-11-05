package com.xiaoxingxing.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoxingxing.demo.constant.Constant;
import com.xiaoxingxing.demo.util.HttpUtils;
import com.xiaoxingxing.demo.util.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 账户测试
 */
public class AccountTest extends BaseTest {

    /**
     * 余额查询
     */
    @Test
    public void queryBalance() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("requestTime", DateUtils.format(new Date(), "yyyyMMddHHmmss"));
            System.out.println(JSON.toJSONString(params));
            params = convert(params);
            System.out.println(JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.BALANCE_QUERY_URL, JSON.toJSONString(params));
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
