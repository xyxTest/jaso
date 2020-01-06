package com.yaj.core.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Cookie
public class UTF8Utils {
    
	public static void main(String[] args) {
		System.out.println(removeByte("🍁🍁"));
	}
	
    //去除异常字节
    public static String removeByte(String s) {
    	if(s==null) {
    		return "";
    	}
    	else {
    		return new String(UTF8Utils.remove4BytesUTF8Char(s));
    	}
    }
    
    public static Map<String, Integer> hexMap = new HashMap<String, Integer>();
    public static Map<String, Integer> byteMap = new HashMap<String, Integer>();
    
    static {
        hexMap.put("0", 2);
        hexMap.put("1", 2);
        hexMap.put("2", 2);
        hexMap.put("3", 2);
        hexMap.put("4", 2);
        hexMap.put("5", 2);
        hexMap.put("6", 2);
        hexMap.put("7", 2);
        hexMap.put("c", 4);
        hexMap.put("d", 4);
        hexMap.put("e", 6);
        hexMap.put("f", 8);
        
        byteMap.put("0", 1);
        byteMap.put("1", 1);
        byteMap.put("2", 1);
        byteMap.put("3", 1);
        byteMap.put("4", 1);
        byteMap.put("5", 1);
        byteMap.put("6", 1);
        byteMap.put("7", 1);
        byteMap.put("c", 2);
        byteMap.put("d", 2);
        byteMap.put("e", 3);
        byteMap.put("f", 4);
    }
    
    /**
     * 是否包含4字节UTF-8编码的字符（先转换16进制再判断）
     * @param s 字符串
     * @return 是否包含4字节UTF-8编码的字符
     */
    public static boolean contains4BytesChar(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        
        String hex = UTF8Utils.bytesToHex(s.getBytes());
        System.out.println("full hex : " + hex);
        
        String firstChar = null;
        while (hex != null && hex.length() > 1) {
            firstChar = hex.substring(0, 1);
            System.out.println("firstChar : " + firstChar);
            
            if ("f".equals(firstChar)) {
                System.out.println("it is f start, it is 4 bytes, return.");
                return true;
            }
            
            if (hexMap.get(firstChar) == null) {
                System.out.println("it is f start, it is 4 bytes, return.");
                // todo, throw exception for this case
                return false;
            }
            
            hex = hex.substring(hexMap.get(firstChar), hex.length());
            System.out.println("remain hex : " + hex);
        }
        
        return false;
    }
    
    /**
     * 是否包含4字节UTF-8编码的字符
     * @param s 字符串
     * @return 是否包含4字节UTF-8编码的字符
     */
    public static boolean contains4BytesChar2(String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        
        byte[] bytes = s.getBytes();
        
        if (bytes == null || bytes.length == 0) {
            return false;
        }
        
        int index = 0;
        byte b;
        String hex = null;
        String firstChar = null;
        int step;
        while (index <= bytes.length - 1) {
            System.out.println("while loop, index : " + index);
            b = bytes[index];
            
            hex = byteToHex(b);
            if (hex == null || hex.length() < 2) {
                System.out.println("fail to check whether contains 4 bytes char(1 byte hex char too short), default return false.");
                // todo, throw exception for this case
                return false;
            }
            
            firstChar = hex.substring(0, 1);
            
            if (firstChar.equals("f")) {
                return true;
            }
            
            if (byteMap.get(firstChar) == null) {
                System.out.println("fail to check whether contains 4 bytes char(no firstchar mapping), default return false.");
                // todo, throw exception for this case
                return false;
            }
            
            step = byteMap.get(firstChar);
            System.out.println("while loop, index : " + index + ", step : " + step);
            index = index + step;
        }
        
        return false;
    }
    
    /**
     * 去除4字节UTF-8编码的字符
     * @param s 字符串
     * @return 已去除4字节UTF-8编码的字符
     */
    public static byte[] remove4BytesUTF8Char(String s) {
        byte[] bytes = s.getBytes();
        byte[] removedBytes = new byte[bytes.length];
        int index = 0;
        
        String hex = null;
        String firstChar = null;
        for (int i = 0; i < bytes.length; ) {
            hex = UTF8Utils.byteToHex(bytes[i]);
            
            if (hex == null || hex.length() < 2) {
                System.out.println("fail to check whether contains 4 bytes char(1 byte hex char too short), default return false.");
                // todo, throw exception for this case
                return null;
            }
            
            firstChar = hex.substring(0, 1);
            
            if (byteMap.get(firstChar) == null) {
                System.out.println("fail to check whether contains 4 bytes char(no firstchar mapping), default return false.");
                // todo, throw exception for this case
                return null;
            }
            
            if (firstChar.equals("f")) {
                for (int j = 0; j < byteMap.get(firstChar); j++) {
                    i++;
                }
                continue;
            }
            
            for (int j = 0; j < byteMap.get(firstChar); j++) {
                removedBytes[index++] = bytes[i++];
            }
        }
        
        return Arrays.copyOfRange(removedBytes, 0, index);
    }
    
    /**
     * 将字符串的16进制转换为HEX，并按每个字符的16进制分隔格式化
     * @param s 字符串
     */
    public static String splitForReading(String s) {
        if (s == null || s.trim().length() == 0) {
            return "";
        }
        
        String hex = UTF8Utils.bytesToHex(s.getBytes());
        System.out.println("full hex : " + hex);
        
        if (hex == null || hex.length() == 0) {
            System.out.println("fail to translate the bytes to hex.");
            // todo, throw exception for this case
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int index = 0;
        
        String firstChar = null;
        String splittedString = null;
        while (index < hex.length()) {
            firstChar = hex.substring(index, index + 1);
            
            if (hexMap.get(firstChar) == null) {
                System.out.println("fail to check whether contains 4 bytes char(no firstchar mapping), default return false.");
                // todo, throw exception for this case
                return "";
            }
            
            splittedString = hex.substring(index, index + hexMap.get(firstChar));
            sb.append(splittedString).append(" ");
            index = index + hexMap.get(firstChar);
        }
        
        System.out.println("formated sb : " + sb);
        return sb.toString();
    }
    
    /**
     * 字节数组转十六进制
     * @param bytes 字节数组
     * @return 十六进制
     */
    public static String bytesToHex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int r = bytes[i] & 0xFF;
            
            String hexResult = Integer.toHexString(r);
            if (hexResult.length() < 2) {
                sb.append(0); // 前补0
            }
            sb.append(hexResult);
        }
        
        return sb.toString();
    }
    
    /**
     * 字节转十六进制
     * @param b 字节
     * @return 十六进制
     */
    public static String byteToHex(byte b) {
        int r = b & 0xFF;
        String hexResult = Integer.toHexString(r);
        
        StringBuilder sb = new StringBuilder();
        if (hexResult.length() < 2) {
            sb.append(0); // 前补0
        }
        sb.append(hexResult);
        return sb.toString();
    }

}