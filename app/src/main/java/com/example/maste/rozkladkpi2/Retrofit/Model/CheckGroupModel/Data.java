package com.example.maste.rozkladkpi2.Retrofit.Model.CheckGroupModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("group_full_name")
    @Expose
    private String groupFullName;
    @SerializedName("group_prefix")
    @Expose
    private String groupPrefix;
    @SerializedName("group_okr")
    @Expose
    private String groupOkr;
    @SerializedName("group_type")
    @Expose
    private String groupType;
    @SerializedName("group_url")
    @Expose
    private String groupUrl;

    public String getGroupId() {
        return groupId;
    }
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupFullName() {
        return groupFullName;
    }
    public void setGroupFullName(String groupFullName) {
        this.groupFullName = groupFullName;
    }

    public String getGroupPrefix() {
        return groupPrefix;
    }
    public void setGroupPrefix(String groupPrefix) {
        this.groupPrefix = groupPrefix;
    }

    public String getGroupOkr() {
        return groupOkr;
    }
    public void setGroupOkr(String groupOkr) {
        this.groupOkr = groupOkr;
    }

    public String getGroupType() {
        return groupType;
    }
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getGroupUrl() {
        return groupUrl;
    }
    public void setGroupUrl(String groupUrl) {
        this.groupUrl = groupUrl;
    }
}
