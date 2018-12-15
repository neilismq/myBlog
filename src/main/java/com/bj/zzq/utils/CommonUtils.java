package com.bj.zzq.utils;

import java.util.UUID;

public class CommonUtils {
    public static String newUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
