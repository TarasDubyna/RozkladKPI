package com.example.maste.rozkladkpi2.Retrofit.Model.CheckGroupModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckGroupModel {

    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;
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
    private Data data;

    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
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

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

}
