package com.tz.demo.JmeterTools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tz.demo.util.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class QueryBalance {
    public static JSONObject queryBalance(String merchantNo) {
        Map<String, String> params = new HashMap<>();
        try {
            params.put("requestTime", DateUtils.format(new Date(), "yyyyMMddHHmmss"));
            params = BaseData.convert(params, merchantNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(JSON.toJSONString(params));
    }

    public static void main(String[] args) {
        System.out.println(QueryBalance.queryBalance("1111"));
    }
}
