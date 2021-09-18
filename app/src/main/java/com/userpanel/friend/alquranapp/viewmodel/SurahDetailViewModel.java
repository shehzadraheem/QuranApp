package com.userpanel.friend.alquranapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.userpanel.friend.alquranapp.repository.SurahDetailRepo;
import com.userpanel.friend.alquranapp.response.SurahDetailResponse;

public class SurahDetailViewModel extends ViewModel {

    public SurahDetailRepo surahDetailRepo;

    public SurahDetailViewModel() {
        surahDetailRepo = new SurahDetailRepo();
    }
    public LiveData<SurahDetailResponse> getSurahDetail(String lan , int id){
        return surahDetailRepo.getSurahDetail(lan,id);
    }
}
