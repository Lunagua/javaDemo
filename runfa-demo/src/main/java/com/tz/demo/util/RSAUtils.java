package com.tz.demo.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA工具类
 */
public class RSAUtils {

    private static final String ALGORITHM = "RSA";

    public final static String MD5_WITH_RSA = "MD5withRSA";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 通过私钥加密
     *
     * @param content    加密内容
     * @param privateKey 私钥
     * @return 加密结果
     */
    public static String encryptByPrivateKey(String content, String privateKey) throws InvalidKeySpecException, IOException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException {
        Key key = parsePrivateKey(privateKey);
        return new String(encrypt(content.getBytes("UTF-8"), key), "UTF-8");
    }

    /**
     * 通过公钥解密
     *
     * @param content   解密内容
     * @param publicKey 公钥
     * @return 解密结果
     */
    public static String decryptByPublicKey(String content, String publicKey) throws InvalidKeySpecException, IOException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException {
        Key key = parsePublicKey(publicKey);
        return new String(decrypt(content.getBytes("UTF-8"), key), "UTF-8");
    }

    /**
     * RSA公钥转换
     *
     * @param publicKeyStr 公钥字符串
     * @return 公钥
     * @throws InvalidKeySpecException
     */
    public static PublicKey parsePublicKey(String publicKeyStr) throws InvalidKeySpecException {
        PublicKey publicKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            byte[] encodedKey = Base64Utils.decodeFromString(publicKeyStr);
            publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
        } catch (NoSuchAlgorithmException e) {
        }
        return publicKey;
    }

    /**
     * RSA私钥转换
     *
     * @param privateKeyStr 私钥字符串
     * @return 私钥
     * @throws InvalidKeySpecException
     */
    public static PrivateKey parsePrivateKey(String privateKeyStr) throws InvalidKeySpecException {
        PrivateKey privateKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

            byte[] encodedKey = Base64Utils.decodeFromString(privateKeyStr);
            privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
        } catch (NoSuchAlgorithmException e) {
        }
        return privateKey;
    }

    /**
     * RSA加密
     *
     * @param content 加密内容
     * @param key     秘钥
     * @return 加密结果
     */
    public static byte[] encrypt(byte[] content, Key key) throws NoSuchAlgorithmException, IllegalBlockSizeException, IOException, InvalidKeyException, BadPaddingException {
        try {
            // 初始化参数
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            // 对数据进行分断加密
            int length = content.length;
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                for (int offSet = 0; offSet < length; offSet += MAX_ENCRYPT_BLOCK) {
                    out.write(cipher.doFinal(content, offSet, Math.min(MAX_ENCRYPT_BLOCK, length - offSet)));
                }
                return Base64.getEncoder().encode(out.toByteArray());
            }
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA解密
     *
     * @param content 解密内容
     * @param key     秘钥
     * @return 解密结果
     */
    public static byte[] decrypt(byte[] content, Key key) throws NoSuchAlgorithmException, IllegalBlockSizeException, IOException, InvalidKeyException, BadPaddingException {
        try {
            // 初始化参数
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            content = Base64Utils.decode(content);
            // 对数据进行分断加密
            int length = content.length;
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                for (int offSet = 0; offSet < length; offSet += MAX_DECRYPT_BLOCK) {
                    out.write(cipher.doFinal(content, offSet, Math.min(MAX_DECRYPT_BLOCK, length - offSet)));
                }
                return out.toByteArray();
            }
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 签名
     *
     * @param data          待签名数据
     * @param dataCharset   待签名数据转字节流字符编码
     * @param privateKey    私钥
     * @param signAlgorithm 签名算法
     * @return 签名
     */
    public static String sign(String data, String dataCharset, String privateKey, String signAlgorithm) throws Exception {
        PrivateKey key = parsePrivateKey(privateKey);
        return sign(data, dataCharset, key, signAlgorithm);
    }

    /**
     * 签名
     *
     * @param data          待签名数据
     * @param dataCharset   待签名数据转字节流字符编码
     * @param privateKey    私钥
     * @param signAlgorithm 签名算法
     * @return 签名
     */
    public static String sign(String data, String dataCharset, PrivateKey privateKey, String signAlgorithm) throws Exception {
        return sign(data.getBytes(dataCharset), privateKey, signAlgorithm);
    }

    /**
     * 签名
     *
     * @param data          待签名数据
     * @param privateKey    私钥
     * @param signAlgorithm 签名算法
     * @return 签名
     */
    public static String sign(byte[] data, PrivateKey privateKey, String signAlgorithm) throws Exception {
        Signature signature = Signature.getInstance(signAlgorithm);
        signature.initSign(privateKey);
        signature.update(data);
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, String srcDataCharset, String publicKey, String sign, String signCharset, String signAlgorithm) throws Exception {
        return verify(srcData, srcDataCharset, parsePublicKey(publicKey), sign, signCharset, signAlgorithm);
    }


    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, String srcDataCharset, PublicKey publicKey, String sign, String signCharset, String signAlgorithm) throws Exception {
        Signature signature = Signature.getInstance(signAlgorithm);
        signature.initVerify(publicKey);
        signature.update(srcData.getBytes(srcDataCharset));
        return signature.verify(org.apache.commons.codec.binary.Base64.decodeBase64(sign.getBytes(signCharset)));
    }
}
