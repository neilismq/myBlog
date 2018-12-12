package com.bj.zzq.controller;

import com.bj.zzq.model.*;
import com.bj.zzq.service.ArticleService;
import com.bj.zzq.service.ArticleTagService;
import com.bj.zzq.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleTagService articleTagService;
    @Autowired
    private TagService tagService;

    @RequestMapping("/")
    public String test(Map map) {
        ArticleEntityExample example = new ArticleEntityExample();
        example.setOrderByClause("create_time desc");
        List<ArticleEntity> list = articleService.selectByExample(example);
        if (list != null && list.size() > 0) {
            ArticleEntity articleEntity = list.get(0);
            map.put("article", articleEntity);
            ArticleTagEntityExample articleTagEntityExample = new ArticleTagEntityExample();
            articleTagEntityExample.createCriteria().andArticleIdEqualTo(articleEntity.getId());
            List<ArticleTagEntity> articleTagEntities = articleTagService.selectByExample(articleTagEntityExample);
            if (articleTagEntities != null && articleTagEntities.size() > 0) {
                TagEntityExample tagEntityExample = new TagEntityExample();
                tagEntityExample.createCriteria().andIdEqualTo(articleTagEntities.get(0).getTagId());
                List<TagEntity> tagEntities = tagService.selectByExample(tagEntityExample);
                if (tagEntities != null && tagEntities.size() > 0) {
                    TagEntity tagEntity = tagEntities.get(0);
                    map.put("tag",tagEntities);
                }
            }
            if (list.size() > 1) {
                ArticleEntity preArticle = list.get(1);
                map.put("preArticle", preArticle);
            }
        }
        return "article";
    }

    @RequestMapping("/archives")
    public String archives() {
        return "blog/archives";
    }

}
