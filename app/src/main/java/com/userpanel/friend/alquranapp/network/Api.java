package com.userpanel.friend.alquranapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static Retrofit instance;

    public static Retrofit getRetrofit(){
        if(instance==null){
            instance = new Retrofit.Builder().baseUrl("http://api.alquran.cloud/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }

    public static Retrofit getInstance(){
        if(instance!=null){
            instance=null;
        }
        instance = new Retrofit.Builder().baseUrl("https://quranenc.com/api/translation/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }

    public static Retrofit getTafseerBookInstance(){
        if(instance!=null){
            instance=null;
        }
        instance = new Retrofit.Builder().baseUrl("http://api.quran-tafseer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }

    public static Retrofit getTafseerInstance(){
        if(instance!=null){
            instance=null;
        }
        instance = new Retrofit.Builder().baseUrl("http://api.quran-tafseer.com/tafseer/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }

    public static Retrofit getAyaOfTheDay(){
        if(instance!=null){
            instance=null;
        }
        instance = new Retrofit.Builder().baseUrl("https://api.alquran.cloud/v1/ayah/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return instance;
    }
}
