package hengXinPay;


import java.text.DecimalFormat;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class creatDaifu {

    private static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static String getMD5Data(String publicKey, String signKey, String name, String bankAccountNo, String merOrderNo) {
        SortedMap<String, Object> MapData = new TreeMap<String, Object>();
        long submitTime = System.currentTimeMillis();
        DecimalFormat df = new DecimalFormat("######0.00");
        MapData.put("amount", df.format((Math.random() * 1 + Math.random() + 100)));
        MapData.put("merOrderNo", merOrderNo);
        MapData.put("submitTime", submitTime);
        MapData.put("notifyUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
        MapData.put("bankCode", "ICBC");
        MapData.put("bankAccountNo", bankAccountNo); //"622558180300381870" + new Random().nextInt(100));
        MapData.put("bankAccountName", name);

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
        MapData.put("bankBranchName", "深圳支行");
        MapData.put("remarks", "袁达");
        String data = "";
        try {
            data = RSACoder.encryptByPublicKey(JSON.toJSONString(MapData), publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getMD5Data(String publicKey, String signKey, String name, String bankAccountNo) {
        return getMD5Data(publicKey, signKey, name, bankAccountNo, getUUID());
    }

    public static String getMD5Data(String publicKey, String signKey, String name) {
        return getMD5Data(publicKey, signKey, name, "622558180300381870" + new Random().nextInt(100));
    }

    public static String getMD5Data(String publicKey, String signKey) {
        return getMD5Data(publicKey, signKey, "袁达");
    }
}
