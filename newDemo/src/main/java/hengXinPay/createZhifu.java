package hengXinPay;


import java.text.DecimalFormat;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class createZhifu {
    public static long submitTime = 0;

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static String getMD5Data(String publicKey, String signKey, String amount, String id, String no) {
        SortedMap<String, Object> MapData = new TreeMap<String, Object>();
        submitTime = System.currentTimeMillis();
        MapData.put("amount", amount);
        MapData.put("merOrderNo", getUUID());
        MapData.put("payType", "ebank");
        MapData.put("notifyUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
        MapData.put("expireTime", 10);
        MapData.put("bankCode", "ICBC");
        MapData.put("orderIp", "192.168.23.55");
        MapData.put("submitTime", submitTime);

        JSONObject joo1 = (JSONObject) JSONObject.toJSON(MapData);
        System.out.println(joo1.toString());

        StringBuffer buff = new StringBuffer();
        for (Entry<String, Object> en : MapData.entrySet()) {
            buff.append(en.getKey()).append("=").append(en.getValue()).append("&");
        }
        String subbuff = buff.toString().substring(0, buff.toString().length() - 1) + "&key=" + signKey;
        System.out.println(subbuff.toString());
        String key = MD5Util.MD5(subbuff, "UTF-8");
        MapData.put("sign", key);
        MapData.put("returnViewUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
        MapData.put("remarks", "{\"id\":" + id + ",\"no\": " + no + "}");
        String data = "";
        try {
            data = RSACoder.encryptByPublicKey(JSON.toJSONString(MapData), publicKey);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    public static String getMD5Data(String publicKey, String signKey, String id, String no) {
        return getMD5Data(publicKey, signKey, new DecimalFormat("######0.00").format((Math.random() * 1 + Math.random() + 100)), id, no);
    }

    public static String getMD5Data(String publicKey, String signKey, String amount) {
        return getMD5Data(publicKey, signKey, amount, "1", "1");
    }

    public static String getMD5Data(String publicKey, String signKey) {
        DecimalFormat df = new DecimalFormat("######0.00");
        String amount = df.format((Math.random() * 1 + Math.random() + 100));
        return getMD5Data(publicKey, signKey, amount);
    }


}
