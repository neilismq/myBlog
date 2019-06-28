package com.bj.zzq.config;

import com.bj.zzq.mapper.SettingEntityMapper;
import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;
import com.bj.zzq.model.SettingEntity;
import com.bj.zzq.service.ArticleService;
import com.bj.zzq.service.SettingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

@Component("siteStatistics")
public class SiteStatisticsFilter implements Filter {

    /**
     * 网站访问量
     */
    private static AtomicInteger siteCount = new AtomicInteger();
    /**
     * 文章访问量
     */
    private static HashMap<String, AtomicInteger> articleCount = new HashMap<>();

    private static ConcurrentHashMap<String, Set<String>> articleIpCaches = new ConcurrentHashMap<>();
    private static Set<String> ipCaches = new HashSet<>();
    @Autowired
    private SettingService settingService;

    @Autowired
    private ArticleService articleService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initWebVisitCount();
        Timer timer = new Timer();
        AppTimerTask timerTask = new AppTimerTask(settingService, articleService);
        timer.schedule(timerTask, 60 * 1000, 5 * 1000);
        ClearCacheTask clearCacheTask = new ClearCacheTask();
        timer.schedule(clearCacheTask, 30 * 60 * 1000, 24 * 60 * 60 * 1000);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doStatistics((HttpServletRequest) servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void initWebVisitCount() {
        List<SettingEntity> settingEntities = settingService.selectAppInfo();
        for (int i = 0; i < settingEntities.size(); i++) {
            SettingEntity settingEntity = settingEntities.get(i);
            String key = settingEntity.getAppKey();
            if ("visitCount".equals(key)) {
                siteCount.set(Integer.valueOf(settingEntity.getAppValue()));
            }
        }
        ArticleEntityExample articleEntityExample = new ArticleEntityExample();
        List<ArticleEntity> articleEntities = articleService.selectByExample(articleEntityExample);
        for (int i = 0; i < articleEntities.size(); i++) {
            ArticleEntity articleEntity = articleEntities.get(i);
            AtomicInteger atomicInteger = new AtomicInteger(articleEntity.getVisitCount() == null ? 0 : articleEntity.getVisitCount());
            articleCount.put(articleEntity.getId(), atomicInteger);
        }
    }

    private void doStatistics(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();

        if (!ipCaches.contains(remoteAddr)) {
            siteCount.incrementAndGet();
            ipCaches.add(remoteAddr);
        }
        String contextPath = request.getServletPath();
        if (contextPath.startsWith("/front/article/")) {
            String[] strs = contextPath.split("/");
            if (strs[3] != null && strs[3].length() == 32) {
                Set<String> articleDetailIps = articleIpCaches.get(strs[3]);
                if (articleDetailIps == null) {
                    HashSet<String> set = new HashSet<>();
                    set.add(remoteAddr);
                    articleIpCaches.put(strs[3], set);
                    AtomicInteger atomicInteger = articleCount.get(strs[3]);
                    if (atomicInteger == null) {
                        AtomicInteger atomicInteger1 = new AtomicInteger(1);
                        articleCount.put(strs[3], atomicInteger1);
                    } else {
                        atomicInteger.incrementAndGet();
                        articleCount.put(strs[3], atomicInteger);
                    }
                } else {
                    if (!articleDetailIps.contains(remoteAddr)) {
                        articleDetailIps.add(remoteAddr);
                        AtomicInteger atomicInteger = articleCount.get(strs[3]);
                        if (atomicInteger == null) {
                            atomicInteger = new AtomicInteger(0);
                        }
                        atomicInteger.incrementAndGet();
                        articleCount.put(strs[3], atomicInteger);
                    }
                }
            }
        }
    }

    public static AtomicInteger getSiteCount() {
        return siteCount;
    }

    public static HashMap<String, AtomicInteger> getArticleCount() {
        return articleCount;
    }

    public static ConcurrentHashMap<String, Set<String>> getArticleIpCaches() {
        return articleIpCaches;
    }

    public static Set<String> getIpCaches() {
        return ipCaches;
    }
}
