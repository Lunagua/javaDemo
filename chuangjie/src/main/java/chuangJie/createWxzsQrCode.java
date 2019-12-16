package chuangJie;


public class createWxzsQrCode {

    private String amount = BaseData.getAmount();

    public static Object getAmount() {
        return BaseData.getMapData().get("amount");
    }

    public static String getMD5Data(String publicKey, String signKey, String officialMode) {
        return getMD5Data(publicKey, signKey, new createWxzsQrCode().amount, officialMode);
    }

    public static String getMD5Data(String publicKey, String signKey, String merOrderNo, String amount, String notifyUrl, String officialMode) {
        String payType = "wxzsQrCode";
        long submitTime = BaseData.getCurrentTime();
        String returnViewUrl = "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback";
        String remarks = "微信赞赏码订单";
        return BaseData.getMD5Data(publicKey, signKey, amount, payType, merOrderNo,
                notifyUrl, submitTime, returnViewUrl, remarks, officialMode);
    }

    public static String getMD5Data(String publicKey, String signKey, String amount, String notifyUrl, String officialMode) {
        String merOrderNo = BaseData.getUUID();
        return getMD5Data(publicKey, signKey, merOrderNo, amount, notifyUrl, officialMode);
    }

    public static String getMD5Data(String publicKey, String signKey, String amount, String officialMode) {
        String notifyUrl = "http://apitest.caimengpay.com/api/payOrder/testSuccessCallback";
        return getMD5Data(publicKey, signKey, amount, notifyUrl, officialMode);
    }

    public static String getCurrentTime() {
        return BaseData.getFormatTime();
    }

    public static void main(String[] args) {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaTKqTix3IVM1dnxW+D3rJPKP7hR54ICtZzh8JWG3zvb6CnEy6U8b2xKJ6AF6ESPitvgZp6AOBYdqCK1OVuW5xlEdOH0oRbMaGw2m2WrVzMIO4jHSWwUhZNCpTtR0RsNMBW+eFOhKoISM4WdnnktVbVRmO/p+G6IZXaFO4cEaSFQIDAQAB";
        String MD5 = "6a187320658d05f7a0848098d0495f3f";
        getMD5Data(publicKey, MD5,"1");

    }
}
