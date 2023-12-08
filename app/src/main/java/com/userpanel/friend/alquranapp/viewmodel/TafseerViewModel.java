package com.userpanel.friend.alquranapp.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.userpanel.friend.alquranapp.model.Tafseer;
import com.userpanel.friend.alquranapp.repository.TafseerRepo;


public class TafseerViewModel extends ViewModel {

    public TafseerRepo tafseerRepo;

    public TafseerViewModel() {
        tafseerRepo = new TafseerRepo();
    }
    public LiveData<Tafseer> getTafseer(int tafseerId, int surahNumber, int ayaNumber){
        return tafseerRepo.getTafseer(tafseerId, surahNumber, ayaNumber);
    }
}