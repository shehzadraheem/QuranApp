package com.userpanel.friend.alquranapp.network;

import com.userpanel.friend.alquranapp.model.Tafseer;
import com.userpanel.friend.alquranapp.model.TafseerBook;
import com.userpanel.friend.alquranapp.response.SurahDetailResponse;
import com.userpanel.friend.alquranapp.response.SurahResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("surah")
    Call<SurahResponse> getSurah();

    @GET("sura/{language}/{id}")
    Call<SurahDetailResponse> getSurahDetail(@Path("language")String lan,
                                             @Path("id") int surahId);

    @GET("tafseer")
    Call<List<TafseerBook>> getTafseerBooks();

    @GET("{tafseerId}/{suraNumber}/{ayaNumber}")
    Call<Tafseer> getTafseer(@Path("tafseerId")int tafseerId ,
                             @Path("suraNumber")int suraNumber, @Path("ayaNumber")int ayaNumber);
}
