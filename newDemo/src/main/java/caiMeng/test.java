package caiMeng;

public class test {
    private static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String url = "http://4At6Hm.cjtest.cn/api/payOrder/submit/201912201655381000141/8";
        String res = HttpClient.doGet(url);
        System.out.print(res);
}}
