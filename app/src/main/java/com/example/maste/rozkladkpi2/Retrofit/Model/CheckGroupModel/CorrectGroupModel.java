package com.example.maste.rozkladkpi2.Retrofit.Model.CheckGroupModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CorrectGroupModel {

    @SerializedName("statusCode")
    @Expose
    private String statusCode;

    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}

