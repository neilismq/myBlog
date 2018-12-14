package com.bj.zzq.controller;

import com.bj.zzq.dao.ArticleDao;
import com.bj.zzq.model.*;
import com.bj.zzq.model.dto.CommentResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping
public class ArticleController {
    @Autowired
    private ArticleDao articleDao;


    @RequestMapping("/")
    public String test(Map map) {
        ArticleEntityExample example = new ArticleEntityExample();
        example.setOrderByClause("create_time desc");
        List<ArticleEntity> list = articleDao.selectByExample(example);

        //按日期取最新的文章
        if (list != null && list.size() > 0) {
            ArticleEntity articleEntity = list.get(0);
            map.put("article", articleEntity);

            //当前文章所询标签
            TagEntity tagEntity = articleDao.selecTagByArticleId(articleEntity.getId());
            if (tagEntity != null) {
                map.put("tag", tagEntity);
            }

            //上一篇文章
            if (list.size() > 1) {
                ArticleEntity preArticle = list.get(1);
                map.put("preArticle", preArticle);
            }

            //获取评论和用户信息
            List<CommentResp> CommentResp = articleDao.selectCommentRespByArticleId(articleEntity.getId());
            map.put("comments",CommentResp);
        }

        return "blog/article";
    }

    @RequestMapping("/archives")
    public String archives() {
        return "blog/archives";
    }

}
