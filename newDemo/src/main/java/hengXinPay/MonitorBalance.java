package hengXinPay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class MonitorBalance {


    public static String getMD5Data(String publicKey, String accountBalance, String inAmount, String tradeRemarks, String tradeDate) {

        SortedMap<String, Object> MapData = new TreeMap<String, Object>();
        MapData.put("accountBalance", accountBalance);
        MapData.put("inAmount", inAmount);
        MapData.put("inBankAccountNo", "6225581803003818" + new Random().nextInt(10000));
        MapData.put("inBankName", "ICBC");
        MapData.put("otherAccountName", "张三");
        MapData.put("otherAccountNo", "6225581803003810" + new Random().nextInt(10000));
        MapData.put("otherProvince", "张家界");
        MapData.put("outAmount", "" + new Random().nextInt(1000));
        MapData.put("tradeBank", "ICBC");
        MapData.put("tradeDate", tradeDate);
        MapData.put("tradeRemarks", tradeRemarks);

        List record = new ArrayList();
        record.add(MapData);

        SortedMap<String, Object> rsaData = new TreeMap<String, Object>();
        rsaData.put("record", record);
        JSONObject obj = new JSONObject(rsaData);
        System.out.println(JSONObject.toJSONString(obj));

        String data = "";
        try {
            data = RSACoder.encryptByPublicKey(JSON.toJSONString(obj), publicKey);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    public static String getMD5Data(String publicKey, String accountBalance, String inAmount, String tradeRemarks) {
        String tradeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return getMD5Data(publicKey, accountBalance, inAmount, tradeRemarks, tradeDate);
    }

    public static String getMD5Data(String publicKey, String accountBalance) {
        String inAmount = "" + new Random().nextInt(1000);
        String tradeRemarks = "转账";
        return getMD5Data(publicKey, accountBalance, inAmount, tradeRemarks);
    }

    public static void main(String[] args) {
        String res = getMD5Data("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYgXwLXgKKLHnOguVHIUzpQuMgva4UdEiSZs4qoYF2kBMj8rFoUcZ51Ot0g1WzE6LRGNciOgRSHzRsVpehKQItUCUzx2X1Z9amtIKR5GPXwHzHVCZMcl6YNXUvOJJL9mVJii4QOc4HEma+HyIWAyVqAepo7am6QB7ao8dyq1YVNwIDAQAB", "6666");
        System.out.println(res);


    }

}
