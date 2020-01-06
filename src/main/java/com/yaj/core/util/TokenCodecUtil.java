package com.yaj.core.util;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.yaj.core.util.codec.Base64;
import com.yaj.core.util.codec.EncrypAES;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TokenCodecUtil {
    private static Base64 base64 = new Base64();
    private static EncrypAES encrypAES = null;
    public static String encrypt(String token){
        try {
//            EncrypAES de1 = new EncrypAES();
//            String msg =token;
//            byte[] encontent = de1.Encrytor(msg);
//            System.out.println("明文是:" + msg);
//            System.out.println("加密后:" + new String(encontent));
//            String base64str=new String(base64.encode(encontent));
//            System.out.println("加密后Base64编码:" + base64str);

            byte[] encontent = getEncrypAES().Encrytor(token);
            return new String(base64.encode(encontent));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String decrypt(String token){
        try {

//            EncrypAES de1 = new EncrypAES();
//            String msg =token;
//            byte[] encontent = de1.Encrytor(msg);
//            System.out.println("明文是:" + msg);
//            System.out.println("加密后:" + new String(encontent));
//            String base64str=new String(base64.encode(encontent));
//            System.out.println("加密后Base64编码:" + base64str);
//
//            System.out.println("base64解码后密文:" + new String(base64.decode(base64str.getBytes())));
//            System.out.println("解密后明文:" + new String(de1.Decryptor(base64.decode(base64str.getBytes()))));

            return new String(getEncrypAES().Decryptor(base64.decode(token.getBytes())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static EncrypAES getEncrypAES() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if(encrypAES==null){
            encrypAES = new EncrypAES();
        }
        return encrypAES;
    }
    public static void main(String[] arg){
        String str = encrypt("123");
        System.out.println(decrypt(str));
    }
}
