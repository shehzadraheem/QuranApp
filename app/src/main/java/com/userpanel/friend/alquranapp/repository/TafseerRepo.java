package com.userpanel.friend.alquranapp.repository;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.userpanel.friend.alquranapp.model.Tafseer;
import com.userpanel.friend.alquranapp.model.TafseerBook;
import com.userpanel.friend.alquranapp.network.Api;
import com.userpanel.friend.alquranapp.network.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TafseerRepo {

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public TafseerRepo() {
        jsonPlaceHolderApi = Api.getTafseerInstance().create(JsonPlaceHolderApi.class);
    }

    public LiveData<Tafseer> getTafseer(int tafseerId, int surahNumber, int ayaNumber){
        MutableLiveData<Tafseer> data = new MutableLiveData<>();
        jsonPlaceHolderApi.getTafseer(tafseerId, surahNumber, ayaNumber).enqueue(new Callback<Tafseer>() {
            @Override
            public void onResponse(@NonNull Call<Tafseer> call, @NonNull Response<Tafseer> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Tafseer> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
