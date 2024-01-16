package com.userpanel.friend.alquranapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AyaOfTheDay {
    @SerializedName("code")
    private int code;

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private List<QuranItem> data;

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public List<QuranItem> getData() {
        return data;
    }
}

