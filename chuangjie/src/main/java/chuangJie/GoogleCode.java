package chuangJie;

import org.apache.commons.codec.binary.Base32;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GoogleCode {


    public static String getCode(String secret) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        long timeMsec = System.currentTimeMillis();
        long t = (timeMsec / 1000L) / 30L;
        long hash;
        try {
            hash = verify_code(decodedKey, t);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        String code = Long.toString(hash);
        int lenCode = code.length();
        String googleCode;
        if(lenCode==3){
            googleCode = "000"+code;
        }else if (lenCode==4){
            googleCode = "00"+code;
        }else if (lenCode==5){
            googleCode = "0"+code;
        }else {
            googleCode = code;
        }
        System.out.println("googleCode: " + googleCode);
        return googleCode;
    }

    private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;

        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }
}


