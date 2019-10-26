package com.bj.zzq.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class CommonUtils {
    public static String newUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isImg(String contentType) {
        if ("image/jfif".equalsIgnoreCase(contentType)|| "image/jpeg".equalsIgnoreCase(contentType) || "image/jpg".equalsIgnoreCase(contentType) || "image/png".equalsIgnoreCase(contentType) || "mage/gif".equalsIgnoreCase(contentType)) {
            return true;
        }
        return false;
    }

    public static String getMD5(String str, String salt) {
        String base = str;
        if (StringUtils.isNotBlank(salt)) {
            base += salt;
        }
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public static void main(String[] args) {
        System.out.println(getMD5("123456","3287hdsfi87#fdsfds!@#^^*&fd"));
        String a="dsfdsfdsfjdsfds";
        a.split("\r\n\r\n");
        System.out.println();
    }
}
