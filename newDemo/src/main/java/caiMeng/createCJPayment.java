package caiMeng;


public class createCJPayment {
    private String amount = BaseData.getAmount();

    public static Object getAmount() {
        return BaseData.getMapData().get("amount");
    }

    public static String getMD5Data(String publicKey, String signKey, String payType) {
        return getMD5Data(publicKey, signKey, new createCJPayment().amount, payType);
    }

    public static String getMD5Data(String publicKey, String signKey, String merOrderNo, String amount, String notifyUrl, String payType) {
        long submitTime = BaseData.getCurrentTime();
        String returnViewUrl = "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback";
        String remarks = "";
        if (payType.equals("aliPayQrCode")) {
            remarks = "创捷支付宝扫码";
        } else if (payType.equals("wxBankCardTransferYs")) {
            remarks = "创捷微信转卡";
        } else if (payType.equals("cardToCardQrCodeYs")) {
            remarks = "创捷卡转卡";
        } else if (payType.equals("ysfFixedQrCodeYs")) {
            remarks = "创捷云闪付固码";
        }
        return BaseData.getMD5Data(publicKey, signKey, amount, payType, merOrderNo,
                notifyUrl, submitTime, returnViewUrl, remarks);
    }

    public static String getMD5Data(String publicKey, String signKey, String amount, String notifyUrl, String payType) {
        String merOrderNo = BaseData.getUUID();
        return getMD5Data(publicKey, signKey, merOrderNo, amount, notifyUrl, payType);
    }

    public static String getMD5Data(String publicKey, String signKey, String amount, String payType) {
        String notifyUrl = "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback";
        return getMD5Data(publicKey, signKey, amount, notifyUrl, payType);
    }

    public static String getCurrentTime() {
        return BaseData.getFormatTime();
    }

    public static void main(String[] args) {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaTKqTix3IVM1dnxW+D3rJPKP7hR54ICtZzh8JWG3zvb6CnEy6U8b2xKJ6AF6ESPitvgZp6AOBYdqCK1OVuW5xlEdOH0oRbMaGw2m2WrVzMIO4jHSWwUhZNCpTtR0RsNMBW+eFOhKoISM4WdnnktVbVRmO/p+G6IZXaFO4cEaSFQIDAQAB";
        String MD5 = "6a187320658d05f7a0848098d0495f3f";
//        String payType = "aliPayQrCode";// 原生支付宝扫码
        String payType = "wxBankCardTransferYs";// 原生微信转卡
//        String payType = "cardToCardQrCodeYs";// 原生卡转卡
//        String payType = "ysfFixedQrCodeYs";// 原生云闪付固码
        getMD5Data(publicKey, MD5, payType);
        System.out.println(getCurrentTime());

    }
}
