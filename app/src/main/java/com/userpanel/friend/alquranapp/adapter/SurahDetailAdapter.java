package com.userpanel.friend.alquranapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.userpanel.friend.alquranapp.R;
import com.userpanel.friend.alquranapp.listener.TafseerListener;
import com.userpanel.friend.alquranapp.model.SurahDetail;

import java.util.ArrayList;
import java.util.List;

public class SurahDetailAdapter extends RecyclerView.Adapter<SurahDetailAdapter.ViewHolder> {

    private Context context;
    private List<SurahDetail> list;
    private TafseerListener tafseerListener;

    public SurahDetailAdapter(Context context, List<SurahDetail> list, TafseerListener tafseerListener) {
        this.context = context;
        this.list = list;
        this.tafseerListener = tafseerListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.surah_detail_layout,parent,false);
        return new ViewHolder(view, tafseerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.ayaNo.setText(String.valueOf(list.get(position).getAya()));
        holder.arabicText.setText(list.get(position).getArabic_text());
        holder.translation.setText(list.get(position).getTranslation());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filter(ArrayList<SurahDetail> details){
        list = details;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView ayaNo;
        private final TextView arabicText;
        private final TextView translation;
        private final ImageView tafseerBtn;

        public ViewHolder(@NonNull View itemView, TafseerListener tafseerListener) {
            super(itemView);

            ayaNo = itemView.findViewById(R.id.aya_no);
            arabicText = itemView.findViewById(R.id.arabic_text);
            translation = itemView.findViewById(R.id.translation);
            tafseerBtn = itemView.findViewById(R.id.tafseer_btn);

            tafseerBtn.setOnClickListener(v -> {
                tafseerListener.onTafseerListener(getAdapterPosition());
            });
        }
    }
}
