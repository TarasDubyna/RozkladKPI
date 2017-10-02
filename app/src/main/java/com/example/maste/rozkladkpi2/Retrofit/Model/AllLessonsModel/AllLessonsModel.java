package com.example.maste.rozkladkpi2.Retrofit.Model.AllLessonsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllLessonsModel {

    @SerializedName("statusCode")
    //@Expose
    private String statusCode;
    @SerializedName("timeStamp")
    //@Expose
    private String timeStamp;
    @SerializedName("message")
    //@Expose
    private String message;
    @SerializedName("debugInfo")
    //@Expose
    private String debugInfo;
    @SerializedName("meta")
    //@Expose
    private String meta;
    @SerializedName("data")
    //@Expose
    private List<Datum> data = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
