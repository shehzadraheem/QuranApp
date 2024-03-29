package com.userpanel.friend.alquranapp.model;

import com.google.gson.annotations.SerializedName;

public class TafseerBook {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("language")
    private String language;
    @SerializedName("author")
    private String author;
    @SerializedName("book_name")
    private String bookName;

    // Constructor
    public TafseerBook(int id, String name, String language, String author, String bookName) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.author = author;
        this.bookName = bookName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "TafsirBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}

