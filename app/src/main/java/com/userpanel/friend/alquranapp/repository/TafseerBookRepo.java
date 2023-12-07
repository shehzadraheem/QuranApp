package com.userpanel.friend.alquranapp.repository;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.userpanel.friend.alquranapp.network.Api;
import com.userpanel.friend.alquranapp.network.JsonPlaceHolderApi;
import com.userpanel.friend.alquranapp.response.TafseerBookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TafseerBookRepo {

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public TafseerBookRepo() {
        jsonPlaceHolderApi = Api.getTafseerInstance().create(JsonPlaceHolderApi.class);
    }

    public LiveData<TafseerBookResponse> getTafseerBooks(){
        MutableLiveData<TafseerBookResponse> data = new MutableLiveData<>();
        jsonPlaceHolderApi.getTafseerBooks().enqueue(new Callback<TafseerBookResponse>() {
            @Override
            public void onResponse(Call<TafseerBookResponse> call, Response<TafseerBookResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TafseerBookResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
