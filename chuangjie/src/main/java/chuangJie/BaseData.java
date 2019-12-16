package chuangJie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class BaseData {
    private static SortedMap<String, Object> MapData = new TreeMap<String, Object>();

    static SortedMap<String, Object> getMapData() {
        return MapData;
    }

    static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    static String getFormatTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static String getAmount() {
        Random random = new Random();
        int ran = random.nextInt(10);
        int num;
        if (ran < 5) {
            num = random.nextInt(1000);
        } else if (ran < 8) {
            num = random.nextInt(5000);
        } else {
            num = random.nextInt(20000);
        }
        if (1000 < num && num < 5000) {
            num = (num / 10) * 10;
        } else if (num < 10000 && num >= 5000) {
            num = (num / 100) * 100;
        } else if (num <= 20000 && num >= 10000) {
            num = (num / 1000) * 1000;
        }
        return num + "";
    }


    public static String getMD5Data(String publicKey, String signKey, String amount, String payType, String merOrderNo,
                                    String notifyUrl, long submitTime, String returnViewUrl, String remarks, String officialMode) {
        MapData.clear();
        MapData.put("amount", amount);
        MapData.put("merOrderNo", merOrderNo);
        MapData.put("payType", payType);
        MapData.put("notifyUrl", notifyUrl);
        MapData.put("expireTime", "1");
        MapData.put("bankCode", "ICBC");
        MapData.put("orderIp", "192.168.23.55");
        MapData.put("submitTime", submitTime);
        MapData.put("officialMode",officialMode); //officialMode（1全包 0非全包）

        StringBuffer buff = new StringBuffer();
        for (Map.Entry<String, Object> en : MapData.entrySet()) {
            buff.append(en.getKey()).append("=").append(en.getValue()).append("&");
        }
        String subbuff = buff.toString() + "key=" + signKey;

        MapData.put("sign", MD5Util.MD5(subbuff, "UTF-8"));
        MapData.put("returnViewUrl", returnViewUrl);
        MapData.put("remarks", remarks);

        System.out.printf("==================================%s参数如下================================%n", MapData.get("remarks"));
        System.out.printf("{%s} 签名参数：%s%n", MapData.get("remarks"), subbuff);
        System.out.printf("{%s} 订单金额为：%s%n", MapData.get("remarks"), MapData.get("amount"));
        System.out.printf("{%s} 加密参数：%s%n", MapData.get("remarks"), JSONObject.toJSON(MapData));

        String data = "";
        try {
            data = RSACoder.encryptByPublicKey(JSON.toJSONString(MapData), publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


}
