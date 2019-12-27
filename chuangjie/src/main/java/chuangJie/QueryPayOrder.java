package chuangJie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class QueryPayOrder {
    private static SortedMap<String, Object> MapData = new TreeMap<String, Object>();

    public static String getMD5Data(String publicKey, String signKey, String merOrderNo, String submitTime) {
        MapData.clear();
        MapData.put("merOrderNo", merOrderNo);
        MapData.put("submitTime", submitTime);

        StringBuffer buff = new StringBuffer();

        for (Map.Entry<String, Object> en : MapData.entrySet()) {
            buff.append(en.getKey()).append("=").append(en.getValue()).append("&");
        }
        String subbuff = buff.toString() + "key=" + signKey;
        MapData.put("sign", MD5Util.MD5(subbuff, "UTF-8"));

        System.out.printf("==================================查询参数如下================================%n");
        System.out.printf("查询 签名参数：%s%n", subbuff);
        System.out.printf("查询 加密参数：%s%n", JSONObject.toJSON(MapData));

        String data = "";
        try {
            data = RSACoder.encryptByPublicKey(JSON.toJSONString(MapData), publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
