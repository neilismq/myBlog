package com.bj.zzq.config;

import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;
import com.bj.zzq.service.ArticleService;
import com.bj.zzq.service.SettingService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AppTimerTask extends TimerTask {
    private SettingService settingService;
    private ArticleService articleService;

    public AppTimerTask(SettingService settingService, ArticleService articleService) {
        this.settingService = settingService;
        this.articleService = articleService;
    }

    @Override
    public void run() {
        //更新网站总访问数量
        settingService.updateSiteCount(SiteStatisticsFilter.getSiteCount().get());
        //更新各文章访问数量
        HashMap<String, AtomicInteger> articleCount = SiteStatisticsFilter.getArticleCount();
        Set<Map.Entry<String, AtomicInteger>> entries = articleCount.entrySet();
        Iterator<Map.Entry<String, AtomicInteger>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, AtomicInteger> next = iterator.next();
            String articleId = next.getKey();
            AtomicInteger articleVisitCount = next.getValue();
            ArticleEntity entity = new ArticleEntity();
            entity.setId(articleId);
            entity.setVisitCount(articleVisitCount.get());
            articleService.updateByPrimaryKeySelective(entity);
        }
    }
}
