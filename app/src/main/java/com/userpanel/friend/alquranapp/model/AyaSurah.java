package com.userpanel.friend.alquranapp.model;

import com.google.gson.annotations.SerializedName;

public class AyaSurah {

    @SerializedName("name")
    private String name;

    @SerializedName("englishName")
    private String englishName;


    public String getName() {
        return name;
    }

    public String getEnglishName() {
        return englishName;
    }
}
