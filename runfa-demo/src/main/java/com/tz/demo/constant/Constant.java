package com.tz.demo.constant;

/**
 * 常量类
 */
public class Constant {

    /**
     * 商户号
     */
    public static String MERCHANT_ID = "21910072";

    /**
     * 公钥
     */
    public static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDHi1DV4hKspf0cYCy6gKfThiCFFj9nHqfw3niwFb4TIfr+oF5t9hjYRQZrfre3oAu8J87sdcuMllk23xhVEKGm+Vv58cVXd7a1vOnX7kon7BHhKq0o7dgLeFrqplp67S194us6+VX+ZQshjsCoC0oaVTixpn21x6BTqPcdtENFQIDAQAB";

    /**
     * 私钥
     */
    public static String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALLea4Lr5twwKImiRA8oRTD8LpZ1TcYAn7NS0EYpb/EhfONb7C1HK1YETmfQF+RIq2ZNixZ3NvQ3S5jVkkNWTgsYLZEhiwh9M+mMZtAsHyLTr1ptneRpR9xqJcpzpiLExVYc4tli1WgQgRR0xyiJfsQBQjUxT5R2nudnJUCLydyDAgMBAAECgYEAhIdEBXcgUFjmUEQIRWrDCW5wXk5/14syyL09UB73sCPt0s+TCGFI/KyoI0cNzLkDNgOSYIzoDL7DpnPnsTjLDTNEjkYmb54DtYa4ojsCJ9wGWSFBJneTLJ1ZdB+vIcengX60KB2lXu8e35Lz8ERfzb8wt0f8W2BrJBaH3rPP27ECQQDnZKc2M4UHtIJXIdz3aCxP9rDyS74BN/yuAhCB2HfkiClmDMCS2FK/mR3n4a1NY7jorvjH7tGxaiJn5b0HLdGtAkEAxePd6pma6BreH0CfNqyCWW2KMxJBGa3a5yi6HifgAOMh8pS60s58YccVPA6zzVxU66TylAUq67iRaBPi5ssM7wJBAJOjJgkwMAXth2g+w/Tbc8KFfPrrlszCQydnS879JeobcoaZRlmrSojTEkC8Pk3tfbKAyV0PpJ2VQWSXMdedxXUCQDLXtr59tKUJsiQJcF6Vt+/OCwHQINW+K03U0uzIhe70bFZtDAr5bk+Y3MXPaTbMEC1y9bg+E6b4hrNc2KE/bhUCQQDYAHhSr6BB7qaKopLrfTOhG3j1C/joXQXuLkfHmPQVTTHvb29Td+K/LKcnsrOLHNj76mDda5uUY2qA9CzJb4XN";

    /**
     * 付订单创建地址
     */
    public static String PAY_CREATE_URL = "http://apitest.runfastpay.com/api/pay/generate";

    /**
     * 支付订单查询地址
     */
    public static String PAY_QUERY_URL = "http://apitest.runfastpay.com/api/pay/query";

    /**
     * 代付至银行卡订单创建地址
     */
    public static String REMIT_CREATE_URL = "http://apitest.runfastpay.com/api/remit/generate";

    /**
     * 代付至银行卡订单查询地址
     */
    public static String REMIT_QUERY_URL = "http://apitest.runfastpay.com/api/remit/query";

    /**
     * 账户余额查询地址
     */
    public static String BALANCE_QUERY_URL = "http://apitest.runfastpay.com/api/balance/query";

}
