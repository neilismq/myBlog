package com.bj.zzq.model;

public class SettingEntity {
    private String id;

    private String appKey;

    private String appValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getAppValue() {
        return appValue;
    }

    public void setAppValue(String appValue) {
        this.appValue = appValue == null ? null : appValue.trim();
    }
}