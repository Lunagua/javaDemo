package com.xiaoxingxing.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils {

    /**
     * 时间格式化
     * @param date 时间
     * @param pattern 格式化
     * @return 时间字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
