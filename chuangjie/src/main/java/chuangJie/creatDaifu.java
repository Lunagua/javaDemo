package chuangJie;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.DecimalFormat;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class creatDaifu {
    private static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static String getMD5Data(String publicKey, String signKey, String amount, String bankAccountName, String bankAccountNo) {
        SortedMap<String, Object> MapData = new TreeMap<String, Object>();
        long submitTime = System.currentTimeMillis();
        MapData.put("amount", amount);
        MapData.put("merOrderNo", getUUID());
        MapData.put("submitTime", submitTime);
        MapData.put("notifyUrl", "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback");
        MapData.put("bankCode", "ICBC");
        MapData.put("bankAccountNo", bankAccountNo);
        MapData.put("bankAccountName", bankAccountName);

        StringBuffer buff = new StringBuffer();
        for (Entry<String, Object> en : MapData.entrySet()) {
            buff.append(en.getKey()).append("=").append(en.getValue()).append("&");
        }
        String subbuff = buff.toString() + "key=" + signKey;
        String key = MD5Util.MD5(subbuff, "UTF-8");
        MapData.put("sign", key);
        MapData.put("bankBranchName", "深圳支行");
        MapData.put("remarks", "代付订单");
        String data = "";
        System.out.printf("==================================%s参数如下================================%n", MapData.get("remarks"));
        System.out.printf("{%s} 签名参数：%s%n", MapData.get("remarks"), subbuff);
        System.out.printf("{%s} 订单金额为:%s%n", MapData.get("remarks"), MapData.get("amount"));
        System.out.printf("{%s} 加密参数：%s%n", MapData.get("remarks"), JSONObject.toJSON(MapData));
        try {
            data = RSACoder.encryptByPublicKey(JSON.toJSONString(MapData), publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getMD5Data(String publicKey, String signKey, String amount) {
        String bankAccountNo = "622558180300381870" + new Random().nextInt(100);
        String bankAccountName = "袁达";
        return getMD5Data(publicKey, signKey, amount, bankAccountName, bankAccountNo);
    }

    public static String getMD5Data(String publicKey, String signKey) {
        DecimalFormat df = new DecimalFormat("######0.00");
        String amount = df.format((Math.random() * 1 + Math.random() + 100));
        return getMD5Data(publicKey, signKey, amount);
    }


    public static void main(String[] args) {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaTKqTix3IVM1dnxW+D3rJPKP7hR54ICtZzh8JWG3zvb6CnEy6U8b2xKJ6AF6ESPitvgZp6AOBYdqCK1OVuW5xlEdOH0oRbMaGw2m2WrVzMIO4jHSWwUhZNCpTtR0RsNMBW+eFOhKoISM4WdnnktVbVRmO/p+G6IZXaFO4cEaSFQIDAQAB";
        String MD5 = "6a187320658d05f7a0848098d0495f3f";
//        String amount = "1";
        getMD5Data(publicKey, MD5);
    }
}
