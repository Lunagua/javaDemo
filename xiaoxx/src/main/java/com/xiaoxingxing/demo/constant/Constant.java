package com.xiaoxingxing.demo.constant;

/**
 * 常量类
 */
public class Constant {

    /**
     * 商户号
     */
    public static String MERCHANT_ID = "10004";

    /**
     * 公钥
     */
    public static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC8v5tfq4doLbeI+0rmMrX13cGz6iMpOuKZAWo7eI3/ZdGTW5KZNmc4clNB4RVqe3Hx+BYjssxwKB/7xWYPr7rGOLBMur3wxGx7x9CzuB4CnzcvOTR0l3wnZ8ESwTtkSojhAxRBBzdWMawGZXJ8gFdK8tW9hLmQu+qm4Sf/i9LICQIDAQAB";

    /**
     * 私钥
     */
    public static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMXyOecSpiDVQZD7SJQu7raxKkXEKExi4S4MTrCxgQ9W6IID8UoCcTZB8WJxRS8CJfUvEwkSJzZDbULz/wz4Rslrv47UnVx7tYqaqC0JU3115/iynoTke6Y5HO9EOZYGp+DWzwkmSP8QR0qXATUwSvcNsPSVOW3xPfblhRn+wNBdAgMBAAECgYEAhCpdeum6voVlR9Zf+YD0pkrYfMiiV45V55cUG2oJUCKpD9ZuDwpeaOnSr/YNSXvXuEN8JDxYriSLGX8sto43RCztEffAI/YBv4IcUKsYjGT1ojOakPAhbb+MT5OfOV43+V0zhkSepokG51HTJp4GkerCVs2WpBXkqvEROh4cmgkCQQDi45p+/JIduCAj03AdVrbSMGhVK8Fp0FZwOZUNp2OKXKxzNdCXRcM98e94+GjC77HqGOmxo+kaLY8dk3nrWhGLAkEA31f3EVGUDTK6KJnd+tQYdC1HLncl78eO0o1zarprIcfm0XCNZlLop6ELvEKq7s5qy75Rcds41EBimWK9q7eStwJAMNzXjvWR+jiHI3ALuyXLpaSrM3yC4OjvVKfyA8jC1S90LDOwwoNRsuuBqUnGNgUtCb9nxMwKDYs2QSZboWX6VQJANIaml48dB6GXgHb3asc43RHM/NOOu36uSzxzGXWwPmiaE0WqK5dyGgJZFTSEqNco7LPcya8qBVkDF1nOi7UHrwJAItpT++uzz22AzhG1RUwI/M4BmDXxdIbl1n4SUS96mC6Mjd6FnUiT30SIJsB8G+t0Ag09H8iN6bGqHq8dTMG/UA==";

    /**
     * 银联在线支付订单创建地址
     */
    public static String UPOP_CREATE_URL = "http://apitest.tuozpay.com/api/payOrder/create";

    /**
     * 银联在线支付订单查询地址
     */
    public static String UPOP_QUERY_URL = "http://apitest.tuozpay.com/api/payOrder/query";

    /**
     * 代付至银行卡订单创建地址
     */
    public static String REMIT_CREATE_URL = "http://apitest.tuozpay.com/api/remitOrder/create";

    /**
     * 代付至银行卡订单查询地址
     */
    public static String REMIT_QUERY_URL = "http://apitest.tuozpay.com/api/remitOrder/query";

    /**
     * 账户余额查询地址
     */
    public static String BALANCE_QUERY_URL = "http://apitest.tuozpay.com/api/balance/query";

}
