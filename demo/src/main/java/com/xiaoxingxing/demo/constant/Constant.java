package com.xiaoxingxing.demo.constant;

/**
 * 常量类
 */
public class Constant {

    /**
     * 商户号
     */
    public static String MERCHANT_ID = "10008";

    /**
     * 公钥
     */
    public static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYhRE4kju3WVmhJNs8z4wuduyek1mOsoS/BgInLgo9eG9wGKl3jKUWDhXYx5sbjy8ekzOlyo4M3XSvYkL2PjQZ6niKuy5SIrG0a+jp7Usm+Ex7YWgWoJxFEwf+1vEWVKLQB2BXM+zVRg9f9xoSLeHEqheMJb42z0UchcpHVuV7dQIDAQAB";

    /**
     * 私钥
     */
    public static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJddfEEj/ZEeaaQ1mexIto08hmLrlHjEMWijaZon8tIQYg0LPWQE7ablabkRhfYspI0ALQSsBBWuq2QpkTb+BvvVbqs6kSnTvFJkATzq422grg5fRdoQUI9xTlvVwxE542Srh8Akpu4TrZKo7VFXyS1gQLBleYLGZkDkZRokMKxxAgMBAAECgYB+nrZ9q4THa8CFnE05qo4DUhk9EMLvz3dhiCmjyj1HWmsbbEabr7+5+HlbVnxQtYGlbfs6yEbWeHppXJhcf14I7G57eVANLlxzBWD+NnQg4EHtzMAqJ5BYTzz65PX42c92tJ6TPvlw1UHxndnNiY1NSLo01t29x7hIW377cEtvkQJBANSUqBFiOznvRave3I/rhtIOBH0Obl7dlWh4++v+/v7dONJuiwWsC0dW4DL80CdHC8bWIj4pEq/I+1xiLsyrm4UCQQC2SAOQO9KKFb04eQbiXZ89TLNSE4avP1dVoyS4z4uUa+7Q+z+G5LwACCj/r2xJbQjuBs+A//8r1laNjEU62DL9AkEArkKKH23x9QTyjPhsv8GnzCtXfJhG25cy8egzQvrOV2hLOx0I84Zb3IgX01Qw4fG4cq94dJJA0j6j5tBJYtfN7QJAXlzjc1x0vsVIQp7swGzlDa/SORAgrkJlLNEbJo1S3dAOPqOCnIt3A7E1wD+JGG3YrJhb/ZUI/SGlhvtxr2fS5QJBAIxcv7C3FxNQySnKsPtWsBHvGYFz0y5ZbY9xE+AnBDw7JZOMA6UumtxAr5MdTOBnxqnX5ciuXVXnIfVcrxvh7rc=";

    /**
     * 银联在线支付订单创建地址
     */
    public static String UPOP_CREATE_URL = "http://apitest.henglipay.com/api/upop/create";

    /**
     * 银联在线支付订单查询地址
     */
    public static String UPOP_QUERY_URL = "http://apitest.henglipay.com/api/upop/query";

    /**
     * 代付至银行卡订单创建地址
     */
    public static String REMIT_CREATE_URL = "http://apitest.henglipay.com/api/eBankRemit/create";

    /**
     * 代付至银行卡订单查询地址
     */
    public static String REMIT_QUERY_URL = "http://apitest.henglipay.com/api/eBankRemit/query";

    /**
     * 账户余额查询地址
     */
    public static String BALANCE_QUERY_URL = "http://apitest.henglipay.com/api/balance/query";

}
