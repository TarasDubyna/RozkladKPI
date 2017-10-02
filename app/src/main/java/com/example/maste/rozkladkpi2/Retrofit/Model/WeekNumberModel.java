package com.example.maste.rozkladkpi2.Retrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeekNumberModel {

    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("timeStamp")
    @Expose
    private long timeStamp;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("debugInfo")
    @Expose
    private String debugInfo;
    @SerializedName("meta")
    @Expose
    private String meta;
    @SerializedName("data")
    @Expose
    private int data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

