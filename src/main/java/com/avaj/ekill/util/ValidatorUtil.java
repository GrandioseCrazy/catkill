package com.avaj.ekill.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author: DoubleP
* @Date: 2019/11/4 9:58
* @Description:
*/
public class ValidatorUtil {
    // 以1开头后接10个数字
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

    public static void main(String[] args) {
        System.out.println(isMobile("14121231111"));
    }
}
