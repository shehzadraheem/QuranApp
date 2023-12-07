package com.userpanel.friend.alquranapp.response;
import com.userpanel.friend.alquranapp.model.TafseerBook;
import java.util.List;

public class TafseerBookResponse {

    private List<TafseerBook> tafseerBooks;

    public List<TafseerBook> getTafseerBooks() {
        return tafseerBooks;
    }

    public void setTafseerBooks(List<TafseerBook> tafseerBooks) {
        this.tafseerBooks = tafseerBooks;
    }
}
