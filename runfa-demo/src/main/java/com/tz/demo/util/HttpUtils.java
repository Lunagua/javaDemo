package com.tz.demo.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * HTTP工具类
 */
public class HttpUtils {

    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public static String post(String url, String data) {
        String result = null;

        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(data, "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
