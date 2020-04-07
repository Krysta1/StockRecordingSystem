package com.us.srs.utils;
import java.util.regex.Pattern;
public class ValidatorUtil {
    private static  String REGEX_PHONE="^(\\+?1)?[2-9]\\d{2}[2-9](?!11)\\d{6}$"; //美区正则匹配
    public static  boolean isPhoneNumnber(String phone){
        return Pattern.matches(REGEX_PHONE, phone);
    }
}
