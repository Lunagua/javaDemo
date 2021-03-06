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

    public static String getMD5Data(String publicKey, String signKey, String name, String bankAccountNo, String merOrderNo, String amount) {
        SortedMap<String, Object> MapData = new TreeMap<String, Object>();
        long submitTime = System.currentTimeMillis();

        MapData.put("amount", amount);
        MapData.put("merOrderNo", merOrderNo);
        MapData.put("submitTime", submitTime);
        MapData.put("notifyUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
        MapData.put("bankCode", "ICBC");
        MapData.put("bankAccountNo", bankAccountNo);
        MapData.put("bankAccountName", name);

        StringBuffer buff = new StringBuffer();
        for (Entry<String, Object> en : MapData.entrySet()) {
            buff.append(en.getKey()).append("=").append(en.getValue()).append("&");
        }
        String subbuff = buff.toString().substring(0, buff.toString().length() - 1) + "&key=" + signKey;
        System.out.println(subbuff);
        String key = MD5Util.MD5(subbuff, "UTF-8");
        MapData.put("sign", key);
        MapData.put("bankBranchName", "深圳支行");
        MapData.put("remarks", "袁达");
        System.out.println("签名参数如下：");
        System.out.println(subbuff);
        System.out.println("加密参数如下：");
        System.out.println(MapData.toString());
        String data = "";
        try {
            data = RSACoder.encryptByPublicKey(JSON.toJSONString(MapData), publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getMD5Data(String publicKey, String signKey, String name, String bankAccountNo, String amount) {
        return getMD5Data(publicKey, signKey, name, bankAccountNo, getUUID(), amount);
    }

    public static String getMD5Data(String publicKey, String signKey, String amount) {
        return getMD5Data(publicKey, signKey, "袁达", "622558180300381870" + new Random().nextInt(100), amount);
    }

    public static String getMD5Data(String publicKey, String signKey) {
        DecimalFormat df = new DecimalFormat("######0.00");
        String amount = df.format((Math.random() * 1 + Math.random() + 100));
        return getMD5Data(publicKey, signKey, amount);
    }
}
