package com.bj.zzq.model;

import java.io.Serializable;

public class ArticleTagEntity implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 标签id
     */
    private String tagId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }
}