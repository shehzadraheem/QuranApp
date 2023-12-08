package com.userpanel.friend.alquranapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.userpanel.friend.alquranapp.model.TafseerBook;
import com.userpanel.friend.alquranapp.network.Api;
import com.userpanel.friend.alquranapp.network.JsonPlaceHolderApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TafseerBookRepo {

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public TafseerBookRepo() {
        jsonPlaceHolderApi = Api.getTafseerBookInstance().create(JsonPlaceHolderApi.class);
    }

    public LiveData<List<TafseerBook>> getTafseerBooks(){
        MutableLiveData<List<TafseerBook>> data = new MutableLiveData<>();
        jsonPlaceHolderApi.getTafseerBooks().enqueue(new Callback<List<TafseerBook>>() {
            @Override
            public void onResponse(@NonNull Call<List<TafseerBook>> call, @NonNull Response<List<TafseerBook>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<TafseerBook>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
