package com.bj.zzq.controller;

import com.bj.zzq.config.SiteStatisticsFilter;
import com.bj.zzq.dao.ArticleDao;
import com.bj.zzq.model.*;
import com.bj.zzq.model.dto.ArticleTagResp;
import com.bj.zzq.model.dto.CommentUserResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.*;
import java.util.prefs.BackingStoreException;


@Controller
@RequestMapping(value = "/front")
public class FrontController {

    @Autowired
    private ArticleDao articleDao;


    @RequestMapping(value = "/page/{pageNum}", method = RequestMethod.GET)
    public String articlePage(ModelMap map, @PathVariable Integer pageNum) {
        //默认每页显示10条数据
        int pageSize = 10;
        if (pageNum == null) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        ArticleEntityExample articleEntityExample = new ArticleEntityExample();
        articleEntityExample.setOrderByClause("create_time desc");
        List<ArticleEntity> articleEntities = articleDao.selectArticleByExample(articleEntityExample);
        for (int i = 0; i < articleEntities.size(); i++) {
            ArticleEntity articleEntity = articleEntities.get(i);
            List<TagEntity> tagEntities = articleDao.selectTagsByArticleId(articleEntity.getId());
            articleEntity.setTags(tagEntities);
            //转换显示内容
            String content = articleEntity.getContent();
            String showText = content.split("\r\n\r\n")[0];
            articleEntity.setContent(showText);
        }

        PageInfo<ArticleEntity> pageInfo = new PageInfo<>(articleEntities, 3);
        map.put("pageInfo", pageInfo);
        return "front/index";
    }

    /**
     * 具体文章页面
     *
     * @param map
     * @param articleId
     * @return
     */
    @RequestMapping(value = {"/article/{articleId}"}, method = RequestMethod.GET)
    public String articlePage(Map map, @PathVariable String articleId) {
        ArticleEntityExample example = new ArticleEntityExample();
        example.createCriteria().andIdEqualTo(articleId);
        List<ArticleEntity> list = articleDao.selectArticleByExample(example);
        if (list == null || list.size() == 0) {
            return "error/404";
        }
        ArticleEntity articleEntity = list.get(0);
        //按日期取最新的文章或获取指定的文章
        map.put("article", articleEntity);

        //当前文章所属标签
        //todo:多个标签时如何处理
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
        map.put("readCount", SiteStatisticsFilter.getArticleCount().get(articleId).get());
        return "front/article";
    }

    /**
     * 具体某标签档案页面
     *
     * @param map
     * @param tagId
     * @return
     */
    @RequestMapping(value = "/archives/{tagId}", method = RequestMethod.GET)
    public String archives(Map map, @PathVariable String tagId) {
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.createCriteria().andIdEqualTo(tagId);
        List<TagEntity> tagEntities = articleDao.selecTagByExample(tagEntityExample);
        List<TagEntity> allTags = articleDao.selectAllTagWithArticleCount();
        if (tagEntities == null || tagEntities.size() == 0) {
            return "error/404";
        }
        TagEntity tagEntity = tagEntities.get(0);
        //当前tag
        map.put("currentTag", tagEntity);
        //全部tag
        map.put("tags", allTags);
        String id = tagEntity.getId();
        List<ArticleEntity> articlesList = null;
        if (StringUtils.isBlank(id)) {
            //剪贴板
            articlesList = articleDao.selectAllClipboardArticles();
        } else {
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

        return "front/archives";
    }

    /**
     * 剪贴板档案页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/archives/", method = RequestMethod.GET)
    public String archives(Map map) {
        List<TagEntity> allTags = articleDao.selectAllTagWithArticleCount();
        //全部tag
        map.put("tags", allTags);
        List<ArticleEntity> articlesList = articleDao.selectAllClipboardArticles();

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

        return "front/archives";
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public String comment(ModelMap map, CommentUserResp commentUserResp) throws InterruptedException {
        String content = commentUserResp.getContent().trim();
        if (!content.contains("</blockquote>")) {
            content = "<p>" + content;
        }
        content += "</p>";
        commentUserResp.setContent(content);
        articleDao.insertComment(commentUserResp);
        map.put("comment", commentUserResp);
        return "front/commentSuccess";
    }

    /**
     * 档案首页
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/archivesIndex", method = RequestMethod.GET)
    public String archives_index(Map map) {
        PageHelper.startPage(1, 10);
        HashMap<String, String> mapParam = new HashMap<>();
        mapParam.put("isDraft", "0");
        List<ArticleEntity> list = articleDao.selectArticleWithCommentCount(mapParam);
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
        }
        List<TagEntity> tagEntities = articleDao.selectAllTagWithArticleCount();
        //所有标签的（带文章数量）
        map.put("tags", tagEntities);
        return "front/archivesIndex";
    }

    @RequestMapping(value = "email", method = RequestMethod.GET)
    public String email() {
        return "front/email";
    }

}
