package com.bj.zzq.model.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentUserResp implements Serializable {
    /**
     * id
     */
    private String commentId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论用户id
     */
    private String userId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 文章名称
     */
    private String articleName;

    /**
     * 评论创建时间
     */
    private Date commentCreateTime;

    /**
     * 回复的哪条评论（id）
     */
    private String pid;
    /**
     * 用户名
     */
    private String name;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 个人网址
     */
    private String personalSite;

    /**
     * 创建或者更新时间
     */
    private Date userCreateTime;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Date getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(Date commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalSite() {
        return personalSite;
    }

    public void setPersonalSite(String personalSite) {
        this.personalSite = personalSite;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }
}
