package com.bj.zzq.model.dto;

import java.io.Serializable;
import java.util.Date;

public class ArticleTagResp implements Serializable {
    /**
     * 文章id
     */
    private String articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 创建时间
     */
    private Date articleCreateTime;

    /**
     * 更新时间
     */
    private Date articleUpdateTime;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 标签id
     */
    private String tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 创建时间
     */
    private Date tagCreateTime;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getTagCreateTime() {
        return tagCreateTime;
    }

    public void setTagCreateTime(Date tagCreateTime) {
        this.tagCreateTime = tagCreateTime;
    }
}
