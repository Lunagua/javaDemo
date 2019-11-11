package com.tz.demo.JmeterTools;

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
}
