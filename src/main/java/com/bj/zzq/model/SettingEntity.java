package com.bj.zzq.model;

import java.io.Serializable;

public class SettingEntity implements Serializable {
    /**
     * id
     */
    private String id;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
}