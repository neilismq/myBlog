package com.bj.zzq.config;

import java.util.concurrent.ConcurrentHashMap;

public class TokenCache {
    private final static ConcurrentHashMap cache = new ConcurrentHashMap();

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static Object get(String key) {
        return cache.get(key);
    }

    public static void clear() {
        cache.clear();
    }
}
