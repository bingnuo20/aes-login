package com.dev10000;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
    //密钥，与js保持一致
    private static final String defaultKey = "1qaz2WSX3edc4RFV";
    //算法PKCS5Padding
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    //测试数据
    private static final String testContent = "ABCDEFGH";


    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    //自测
    public static void main(String[] args) throws Exception {
        System.out.println("加密前：" + testContent);

        String encrypt = encrypt(testContent);
        System.out.println("加密后：" + encrypt);

        String decrypt = decrypt(encrypt);
        System.out.println("解密后：" + decrypt);

    }

    //AES加密--为base 64 code
    public static String encrypt(String content) throws Exception {
        return base64Encode(aesEncryptToBytes(content));
    }

    //AES解密--并解密base 64 code
    public static String decrypt(String encryptStr) throws  Exception{
        encryptStr=encryptStr.replaceAll("#","/").replaceAll(" ","+");
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr));
    }

    //base 64 encode编码
    private static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    //base 64 decode解码
    private static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    //AES加密
    private static byte[] aesEncryptToBytes(String content) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(defaultKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    //AES解密
    private static String aesDecryptByBytes(byte[] encryptBytes) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(defaultKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes,"utf-8");
    }

}
