package com.bj.zzq.config;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class ClearCacheTask extends TimerTask {
    @Override
    public void run() {
        //每23小时清除一次缓存
        Set<String> ipCaches = SiteStatisticsFilter.getIpCaches();
        ipCaches.clear();
        ConcurrentHashMap<String, Set<String>> articleIpCaches = SiteStatisticsFilter.getArticleIpCaches();
        Iterator<Map.Entry<String, Set<String>>> iterator = articleIpCaches.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Set<String>> next = iterator.next();
            Set<String> value = next.getValue();
            value.clear();
        }
    }
}
