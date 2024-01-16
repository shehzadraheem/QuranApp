package com.userpanel.friend.alquranapp.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.userpanel.friend.alquranapp.model.AyaOfTheDay;
import com.userpanel.friend.alquranapp.network.Api;
import com.userpanel.friend.alquranapp.network.JsonPlaceHolderApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AyaOfTheDayRepo {

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public AyaOfTheDayRepo() {
        jsonPlaceHolderApi = Api.getAyaOfTheDay().create(JsonPlaceHolderApi.class);
    }

    public LiveData<AyaOfTheDay> getAyaOfTheDay(int number){
        MutableLiveData<AyaOfTheDay> data = new MutableLiveData<>();
        jsonPlaceHolderApi.getAyaOfTheDay(number).enqueue(new Callback<AyaOfTheDay>() {
            @Override
            public void onResponse(@NonNull Call<AyaOfTheDay> call, @NonNull Response<AyaOfTheDay> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<AyaOfTheDay> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
