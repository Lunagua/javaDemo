package com.fancha.tuozhong.entity;

public class Data {
    private String merchantId;
    private String version;
    private String key;
    private String data;

    public Data setData(String data) {
        this.data = data;
        return this;
    }

    public String getData() {
        return data;
    }

    public Data setKey(String key) {
        this.key = key;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Data setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public Data setMerchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public String getMerchantId() {
        return merchantId;
    }
}
