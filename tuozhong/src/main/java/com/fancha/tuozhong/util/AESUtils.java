package com.fancha.tuozhong.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Base64;

/**
 * AES工具类
 */
public class AESUtils {

    private static final String KEY_ALGORITHM = "AES";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    /**
     * 生成key
     *
     * @return 生成结果
     */
    public static String generateKey() {
        String AESKey = "";
        int number;
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < 16; i++) {
            number = Math.abs(random.nextInt()) % 10;
            AESKey += number;
        }
        return AESKey;
    }


    /**
     * 加密
     *
     * @param content 加密内容
     * @param keyStr  秘钥字符串
     * @return 加密结果
     */
    public static String encrypt(String content, String keyStr) throws Exception {
        Key key = new SecretKeySpec(keyStr.getBytes(), KEY_ALGORITHM);
        return encrypt(content, key);
    }

    /**
     * 加密
     *
     * @param content 加密内容
     * @param key     秘钥
     * @return 加密结果
     */
    public static String encrypt(String content, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
        byte[] result = cipher.doFinal(byteContent);// 加密
        return Base64.getEncoder().encodeToString(result);//通过Base64转码返回
    }

    /**
     * 解密
     *
     * @param content 解密内容
     * @param keyStr  秘钥字符串
     * @return 解密结果
     */
    public static String decrypt(String content, String keyStr) throws Exception {
        Key key = new SecretKeySpec(keyStr.getBytes(), KEY_ALGORITHM);
        return decrypt(content, key);
    }

    public static void main(String[] args) {
//        Long mili = System.currentTimeMillis();
        long mili = Long.parseLong("1565331087676");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(mili);
        System.out.println(simpleDateFormat.format(mili));
    }

    /**
     * 解密
     *
     * @param content 解密内容
     * @param key     秘钥
     * @return 解密结果
     */
    public static String decrypt(String content, Key key) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作
        byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
        return new String(result, "utf-8");
    }

}
