package com.example.sampleapplication.apiPackage;

import com.google.gson.annotations.SerializedName;

public class ApiDataClass {

    @SerializedName("userId")
    private final String userId;
    private final String title;
    @SerializedName("body")
    private final String text;

    private Integer id;

    public ApiDataClass(String userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return id;
    }
}
