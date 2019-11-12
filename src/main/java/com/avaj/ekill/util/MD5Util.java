package com.avaj.ekill.util;

import org.springframework.util.DigestUtils;

public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5DigestAsHex(src.getBytes());
    }

    private static final String salt = "1a2b3c4d";

    // 固定的salt
    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    // 数据库的salt
    public static String formPassToDBPass(String formPass,String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(salt);
    }

    public static  String inputPassToDBPass (String input,String saltDb) {
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDBPass(formPass,saltDb);
        return dbPass;
    }

    public static void main(String[] args) {
        //System.out.println(inputPassToFormPass("123")); // c38dc3dcb8f0b43ac8ea6a70b5ec7648
        System.out.println(inputPassToDBPass("123", "1a2b3c4d"));// 1897a69ef451f0991bb85c6e7c35aa31
    }
}
