package com.bj.zzq.controller;

import com.bj.zzq.dao.ArticleDao;
import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PublicController {
    @Autowired
    private ArticleDao articleDao;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        //查找最新文章id
        ArticleEntityExample articleEntityExample = new ArticleEntityExample();
        articleEntityExample.setOrderByClause("create_time");
        articleEntityExample.setLimit(true);
        articleEntityExample.setLimitStart(0);
        articleEntityExample.setLimitOffset(1);
        List<ArticleEntity> articleEntities = articleDao.selectArticleByExample(articleEntityExample);
        ArticleEntity articleEntity = articleEntities.get(0);
        return "redirect:front/article/" + articleEntity.getId();
    }
}
