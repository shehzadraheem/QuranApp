package com.userpanel.friend.alquranapp.model;

import com.google.gson.annotations.SerializedName;

public class QuranItem {

    @SerializedName("numberInSurah")
    private int numberInSurah;

    @SerializedName("text")
    private String text;

    @SerializedName("surah")
    private AyaSurah surah;

    public int getNumberInSurah() {
        return numberInSurah;
    }

    public String getText() {
        return text;
    }

    public AyaSurah getAyaSurah() {
        return surah;
    }
}
