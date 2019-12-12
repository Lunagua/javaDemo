package com.tz.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tz.demo.constant.Constant;
import com.tz.demo.util.DateUtils;
import com.tz.demo.util.HttpUtils;
import com.tz.demo.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * DEMO服务类
 */
@Service
public class DemoService {

    public JSONObject createPayOrder(String merchantOrderNo, String model, String amount, String memberNo, String notifyUrl) {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String> params = new HashMap<>();
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("amount", amount);
            params.put("payType", model);
            params.put("bankCode", "ICBC");
            params.put("memberNo", memberNo);
            params.put("notifyUrl", notifyUrl);
            jsonObject.put("paramsData", JSON.toJSONString(params));
            params = convert(params);
            jsonObject.put("params", JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.PAY_CREATE_URL, JSON.toJSONString(params));
            jsonObject.put("result", result);
            JSONObject data = JSONObject.parseObject(result);
            if (data.getInteger("code") == 0) {
                data = revert(data);
                jsonObject.put("resultData", data.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject queryPayOrder(String merchantOrderNo, Date submitDate) {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String> params = new HashMap<>();
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("submitTime", DateUtils.format(submitDate, "yyyyMMddHHmmss"));
            jsonObject.put("paramsData", JSON.toJSONString(params));
            params = convert(params);
            jsonObject.put("params", JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.PAY_QUERY_URL, JSON.toJSONString(params));
            jsonObject.put("result", result);
            JSONObject data = JSONObject.parseObject(result);
            if (data.getInteger("code") == 0) {
                data = revert(data);
                jsonObject.put("resultData", data.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public JSONObject createRemitOrder(String merchantOrderNo, String bankCode, String amount, String bankcardAccountNo, String bankcardAccountName, String notifyUrl) {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String> params = new HashMap<>();
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("amount", amount);
            params.put("bankCode", bankCode);
            params.put("bankcardAccountNo", bankcardAccountNo);
            params.put("bankcardAccountName", bankcardAccountName);
            params.put("notifyUrl", notifyUrl);
            jsonObject.put("paramsData", JSON.toJSONString(params));
            params = convert(params);
            jsonObject.put("params", JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.REMIT_CREATE_URL, JSON.toJSONString(params));
            jsonObject.put("result", result);
            JSONObject data = JSONObject.parseObject(result);
            if (data.getInteger("code") == 0) {
                data = revert(data);
                jsonObject.put("resultData", data.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public JSONObject queryBalance() {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String> params = new HashMap<>();
            params.put("requestTime", DateUtils.format(new Date(), "yyyyMMddHHmmss"));
            jsonObject.put("paramsData", JSON.toJSONString(params));
            params = convert(params);
            jsonObject.put("params", JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.BALANCE_QUERY_URL, JSON.toJSONString(params));
            jsonObject.put("result", result);
            JSONObject data = JSONObject.parseObject(result);
            if (data.getInteger("code") == 0) {
                data = revert(data);
                jsonObject.put("resultData", data.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public JSONObject queryRemitOrder(String merchantOrderNo, Date submitDate) {
        JSONObject jsonObject = new JSONObject();

        try {
            Map<String, String> params = new HashMap<>();
            params.put("merchantOrderNo", merchantOrderNo);
            params.put("submitTime", DateUtils.format(submitDate, "yyyyMMddHHmmss"));
            jsonObject.put("paramsData", JSON.toJSONString(params));
            params = convert(params);
            jsonObject.put("params", JSON.toJSONString(params));

            String result = HttpUtils.post(Constant.REMIT_QUERY_URL, JSON.toJSONString(params));
            jsonObject.put("result", result);
            JSONObject data = JSONObject.parseObject(result);
            if (data.getInteger("code") == 0) {
                data = revert(data);
                jsonObject.put("resultData", data.toJSONString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }


    /**
     * 参数转换
     *
     * @param data 参数
     * @return 转换结果
     */
    private Map<String, String> convert(Map<String, String> data) throws Exception {
        Map<String, String> result = new TreeMap<>(data);

        result.put("merchantNo", Constant.MERCHANT_ID);
        result.put("version", "1.0.0");
        String signData = result.entrySet().stream().filter(e -> StringUtils.isNotEmpty(e.getValue())).map(e -> e.getKey().concat("=").concat(e.getValue())).collect(Collectors.joining("&"));
        result.put("sign", RSAUtils.sign(signData, "UTF-8", Constant.PRIVATE_KEY, RSAUtils.MD5_WITH_RSA));

        return result;
    }

    /**
     * 参数还原
     *
     * @param data 参数
     * @return 还原结果
     */
    private JSONObject revert(JSONObject data) throws Exception {

        Map<String, Object> map = new TreeMap<>(data);
        String sign = map.remove("sign").toString();
        String signData = map.entrySet().stream().filter(e -> StringUtils.isNotEmpty(e.getValue().toString())).map(e -> e.getKey().concat("=").concat(e.getValue().toString())).collect(Collectors.joining("&"));
        if (!RSAUtils.verify(signData, "UTF-8", Constant.PUBLIC_KEY, sign, "UTF-8", RSAUtils.MD5_WITH_RSA)) {
            throw new RuntimeException("验签失败");
        }
        return data;
    }


    public static void main(String[] args) {
        DemoService demoService = new DemoService();
        JSONObject data = JSONObject.parseObject("{\"data\":\"Fw92LwsSH+ElfRRRTbTRls15P0SKDp171gBrRBqCsttNv0jU1kpLg/G+ggc3BAWcw2QcTzpLvvi2JCoB1zOUNA==\",\"merchantId\":\"10001\",\"merchantOrderNo\":\"20191012144900\",\"key\":\"RxBUGV6V5EJ+utD/w9g8wcZ46UvQSIt+I1BApF8/ozWrEUXvBvbqnDsBYy7rohlZ/QAkr3FtTZoHGterDWXm76ZcakpIbUe8Q3YnSTGAV3OfSbrjq6NVBcNli1ECjS46OBqQsZncDzxTT78pxz8CHJpiaWFXUEojpxAhPL0FMTc=\"}");
        try {
            data = demoService.revert(data);
            System.out.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
