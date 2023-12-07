package com.userpanel.friend.alquranapp.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.userpanel.friend.alquranapp.repository.TafseerBookRepo;
import com.userpanel.friend.alquranapp.response.TafseerBookResponse;

public class TafseerBookViewModel extends ViewModel {

    public TafseerBookRepo tafseerBookRepo;

    public TafseerBookViewModel() {
        tafseerBookRepo = new TafseerBookRepo();
    }
    public LiveData<TafseerBookResponse> getTafseerBooks(){
        return tafseerBookRepo.getTafseerBooks();
    }
}