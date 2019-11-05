package caiMeng;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String url = "http://SOIyWMQ1.ccaimapi.com/api/payOrder/submit/201910311516001036314/30";
        String res = HttpClient.doGet(url);
//        String res = "<span id=\"payAccount_1\">171123</span>";
//        System.out.println(res);
        HttpClient.getRemark(res);
//        Pattern p = Pattern.compile("[\\s\\S]*class=\"content\" id=\"remark\" data=\"remark\">(.*?)</span[\\s\\S]*");
//        Pattern p = Pattern.compile("[\\s\\S]*id=\"payAccount_1\">(.*?)</span[\\s\\S]*");
//        Matcher m = p.matcher(res);
//        while (m.find()) {
//            System.out.println(m.group(1));
//        }
    }
}
