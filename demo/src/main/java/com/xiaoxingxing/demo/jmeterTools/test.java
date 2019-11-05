package com.xiaoxingxing.demo.jmeterTools;

import com.xiaoxingxing.demo.util.AESUtils;
import com.xiaoxingxing.demo.util.RSAUtils;

public class test {
    public static void main(String[] args) throws Exception {
        String data = "OtUh7PpSIhwrVPMpKR42bfdxKhHp/qWBQuQ7ZaJ9I46U/6gGiIrBOFJsYT9g6SJPSM0rKHxhhXEQzCImrVh3ow==";
        String key = "GXi/0sj8FUo2NF3srQJAEG/n/oI3ZluxdF+z7j8fCj4uXOmsgagZ7jtW7a89fie+fVoEuN/+ae7ZLkhdYGU3Qb3OtTrvbRSwmqKcS6KpL4fi0EoMtxTA6iL8WiicFNAZMvN/LVvh/IbfeNvZuA08mDs10R1AyXUlNaAGZR0ztY4=";
        String publickey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCU9sMorCPPlSpAxE3mEg46WI+0d8zA26MtApzDZjAZeMqchZ4TuiDm18XC1oWAbOCvmA5e6PjvshSSr1vOmPmVYC+MUztSAbgaXfmDx+i4M5znSM0d3anQpFN/ulgyHBxtt2QwnCXrOBdYg233N37iLuCgoRZHLRjetlWubscdVwIDAQAB";
        String resKey = RSAUtils.decryptByPublicKey(key,publickey);
        System.out.println(resKey);
        String resData = AESUtils.decrypt(data,resKey);
        System.out.println(resData);
    }
}
