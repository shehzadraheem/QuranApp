package com.userpanel.friend.alquranapp.model;

import com.google.gson.annotations.SerializedName;

public class Tafseer {
    @SerializedName("tafseer_id")
    private int tafseerId;

    @SerializedName("tafseer_name")
    private String tafseerName;

    @SerializedName("ayah_url")
    private String ayahUrl;

    @SerializedName("ayah_number")
    private int ayahNumber;

    @SerializedName("text")
    private String text;

    public Tafseer(int tafseerId, String tafseerName, String ayahUrl, int ayahNumber, String text) {
        this.tafseerId = tafseerId;
        this.tafseerName = tafseerName;
        this.ayahUrl = ayahUrl;
        this.ayahNumber = ayahNumber;
        this.text = text;
    }

    public int getTafseerId() {
        return tafseerId;
    }

    public void setTafseerId(int tafseerId) {
        this.tafseerId = tafseerId;
    }

    public String getTafseerName() {
        return tafseerName;
    }

    public void setTafseerName(String tafseerName) {
        this.tafseerName = tafseerName;
    }

    public String getAyahUrl() {
        return ayahUrl;
    }

    public void setAyahUrl(String ayahUrl) {
        this.ayahUrl = ayahUrl;
    }

    public int getAyahNumber() {
        return ayahNumber;
    }

    public void setAyahNumber(int ayahNumber) {
        this.ayahNumber = ayahNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
