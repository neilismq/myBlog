package com.bj.zzq.controller;

import com.bj.zzq.dao.ArticleDao;
import com.bj.zzq.model.*;
import com.bj.zzq.model.dto.ArticleTagResp;
import com.bj.zzq.model.dto.CommentUserResp;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
@RequestMapping
public class ArticleController {
    @Autowired
    private ArticleDao articleDao;


    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String test(Map map, String articleId) {
        ArticleEntityExample example = new ArticleEntityExample();
        example.setOrderByClause("create_time desc");
        List<ArticleEntity> list = articleDao.selectArticleByExample(example);
        if (list != null && list.size() > 0) {
            ArticleEntity articleEntity = new ArticleEntity();
            if (StringUtils.isBlank(articleId)) {
                articleEntity = list.get(0);
            } else {
                for (int i = 0; i < list.size(); i++) {
                    ArticleEntity articleEntity1 = list.get(i);
                    if (articleEntity1.getId().equals(articleId)) {
                        BeanUtils.copyProperties(articleEntity1, articleEntity);
                        break;
                    }
                }
            }

            //按日期取最新的文章或获取指定的文章
            map.put("article", articleEntity);

            //当前文章所询标签
            TagEntity tagEntity = articleDao.selectTagByArticleId(articleEntity.getId());
            if (tagEntity != null) {
                map.put("tag", tagEntity);
            }

            //下一篇文章
            ArticleEntityExample exampleLimit = new ArticleEntityExample();
            exampleLimit.setOrderByClause(" create_time asc");
            exampleLimit.setLimit(true);
            exampleLimit.createCriteria().andCreateTimeGreaterThan(articleEntity.getCreateTime());
            List<ArticleEntity> nextArticleList = articleDao.selectArticleByExample(exampleLimit);
            if (nextArticleList != null && nextArticleList.size() > 0) {
                map.put("nextArticle", nextArticleList.get(0));
            }

            //上一篇文章
            exampleLimit.getOredCriteria().clear();
            exampleLimit.setOrderByClause(" create_time desc");
            exampleLimit.createCriteria().andCreateTimeLessThan(articleEntity.getCreateTime());
            List<ArticleEntity> preArticleList = articleDao.selectArticleByExample(exampleLimit);
            if (preArticleList != null && preArticleList.size() > 0) {
                map.put("preArticle", preArticleList.get(0));
            }

            //获取评论和用户信息
            List<CommentUserResp> CommentUserResp = articleDao.selectCommentRespByArticleId(articleEntity.getId());
            map.put("comments", CommentUserResp);
        }
        return "blog/article";
    }

    @RequestMapping(value = "/archives", method = RequestMethod.GET)
    public String archives(Map map, String tagId) {

        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.setOrderByClause(" create_time desc");
        List<TagEntity> tagEntities = articleDao.selecTagByExample(tagEntityExample);
        TagEntity tagEntity = new TagEntity();
        if (tagEntities != null && tagEntities.size() > 0) {
            tagEntities.stream().anyMatch(tag -> {
                String id = tag.getId();
                if (id.equals(tagId)) {
                    BeanUtils.copyProperties(tag, tagEntity);
                    return true;
                }
                return false;
            });
        }

        //当前tag
        map.put("currentTag", tagEntity);
        //全部tag
        map.put("tags", tagEntities);
        String id = tagEntity.getId();
        List<ArticleEntity> articlesList = null;
        if (StringUtils.isBlank(id)) {
            //剪贴板
             articlesList = articleDao.selectAllClipboardArticles();
        }else {
             articlesList = articleDao.selectArticlesByTagId(tagId);
        }
        Map<String, Map<String, Object>> articlesMap = new LinkedHashMap();
        if (articlesList != null && articlesList.size() > 0) {
            //当前tag文章数量
            map.put("articleCount", articlesList.size());
            for (int i = 0; i < articlesList.size(); i++) {
                ArticleEntity article = articlesList.get(i);
                Date articleCreateTime = article.getCreateTime();
                int year = articleCreateTime.getYear() + 1900;
                if (!articlesMap.keySet().contains(String.valueOf(year))) {
                    HashMap<String, Object> itemMap = new HashMap<>();
                    itemMap.put("year", String.valueOf(year));
                    LinkedList<Object> articleList = new LinkedList<>();
                    articleList.add(article);
                    itemMap.put("articleList", articleList);
                    articlesMap.put(String.valueOf(year), itemMap);
                    continue;
                } else {
                    Map<String, Object> itemMap = articlesMap.get(String.valueOf(year));
                    List articleList = (List) itemMap.get("articleList");
                    articleList.add(article);
                }
            }
            ArrayList articleList = new ArrayList<>(articlesMap.values());
            map.put("articleList", articleList);
        }

        return "blog/archives";
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public String comment(CommentUserResp commentUserResp) throws InterruptedException {
        String content = commentUserResp.getContent().trim();
        if (!content.contains("</blockquote>")) {
            content = "<p>" + content;
        }
        content += "</p>";
        commentUserResp.setContent(content);
        boolean success = articleDao.insertComment(commentUserResp);
        if (success) {
            return "评论成功！";
        } else {
            return "评论失败，请稍后再试！";
        }
    }

    @RequestMapping(value = "/archivesIndex", method = RequestMethod.GET)
    public String archives_index(Map map) {

        HashMap<Object, Object> param = new HashMap<>();
        param.put("limitStart", 0);
        param.put("limitOffset", 20);
        List<ArticleEntity> list = articleDao.selectArticleWithCommentCount(param);
        if (list != null && list.size() > 0) {
            ArticleEntity articleEntity = list.get(0);
            String content = articleEntity.getContent();
            content = content.substring(0, content.indexOf("\\n") == -1 ? content.length() : content.indexOf("\\n"));
            articleEntity.setContent(content);
            //最新文章
            map.put("article", articleEntity);
            List<CommentUserResp> commentUserResps = articleDao.selectCommentRespByArticleId(articleEntity.getId());
            if (commentUserResps != null && commentUserResps.size() > 0) {
                CommentUserResp commentUserResp = commentUserResps.get(0);
                //最新文章的第一条评论
                map.put("currentArticleFirstComment", commentUserResp);
            }
            //最新文章列表，默认显示20篇
            map.put("recentArticles", list);

            //最新文章所属标签
            TagEntity tagEntity = articleDao.selectTagByArticleId(articleEntity.getId());
            if (tagEntity != null) {
                map.put("tag", tagEntity);
            }
            CommentEntityExample commentEntityExample = new CommentEntityExample();
            commentEntityExample.createCriteria().andArticleIdEqualTo(articleEntity.getId());
            int commentCount = articleDao.selectCommentsCountByExample(commentEntityExample);

            //最新文章评论数量
            map.put("commentCount", commentCount);
            ArticleEntityExample articleEntityExample = new ArticleEntityExample();
            int articleCount = articleDao.selectArticleCountByExample(articleEntityExample);

            //全部文章数量
            map.put("artcileCount", articleCount);
            List<TagEntity> tagEntities = articleDao.selectAllTagWithArticleCount();

            //所有标签的（带文章数量）
            map.put("tags", tagEntities);
        }

        return "blog/archivesIndex";
    }
    @RequestMapping(value = "email",method = RequestMethod.GET)
    public String email(){
        return "blog/email";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "blog/login";
    }
}
