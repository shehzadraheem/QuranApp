package com.userpanel.friend.alquranapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.userpanel.friend.alquranapp.model.AyaOfTheDay;
import com.userpanel.friend.alquranapp.repository.AyaOfTheDayRepo;

public class AyaOfTheDayViewModel extends ViewModel {

    public AyaOfTheDayRepo ayaOfTheDayRepo;

    public AyaOfTheDayViewModel() {
        ayaOfTheDayRepo = new AyaOfTheDayRepo();
    }

    public LiveData<AyaOfTheDay> getAyaOfTheDay(int number){
        return ayaOfTheDayRepo.getAyaOfTheDay(number);
    }
}
