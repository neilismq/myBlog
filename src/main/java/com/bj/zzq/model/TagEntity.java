package com.bj.zzq.model;

import java.io.Serializable;
import java.util.Date;

public class TagEntity implements Serializable {
    /**
     * 标签id
     */
    private String id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}