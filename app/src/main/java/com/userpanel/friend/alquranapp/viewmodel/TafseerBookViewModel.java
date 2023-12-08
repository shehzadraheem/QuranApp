package com.userpanel.friend.alquranapp.viewmodel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.userpanel.friend.alquranapp.model.TafseerBook;
import com.userpanel.friend.alquranapp.repository.TafseerBookRepo;

import java.util.List;

public class TafseerBookViewModel extends ViewModel {

    public TafseerBookRepo tafseerBookRepo;

    public TafseerBookViewModel() {
        tafseerBookRepo = new TafseerBookRepo();
    }
    public LiveData<List<TafseerBook>> getTafseerBooks(){
        return tafseerBookRepo.getTafseerBooks();
    }
}