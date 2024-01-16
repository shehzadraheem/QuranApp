package com.userpanel.friend.alquranapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.userpanel.friend.alquranapp.activities.SurahDetailActivity;
import com.userpanel.friend.alquranapp.adapter.SurahAdapter;
import com.userpanel.friend.alquranapp.common.Common;
import com.userpanel.friend.alquranapp.listener.SurahListener;
import com.userpanel.friend.alquranapp.model.QuranItem;
import com.userpanel.friend.alquranapp.model.Surah;
import com.userpanel.friend.alquranapp.viewmodel.AyaOfTheDayViewModel;
import com.userpanel.friend.alquranapp.viewmodel.SurahViewModel;
import com.userpanel.friend.alquranapp.viewmodel.TafseerViewModel;
import com.userpanel.friend.alquranapp.model.AyaOfTheDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SurahListener {

    private RecyclerView recyclerView;
    private SurahAdapter surahAdapter;
    private List<Surah> list;
    private SurahViewModel surahViewModel;

    private AyaOfTheDayViewModel ayaOfTheDayViewModel;

    private TextView ayaText;

    private TextView ayaTextNo;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerView = findViewById(R.id.surahRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        surahViewModel = new ViewModelProvider(this).get(SurahViewModel.class);
        surahViewModel.getSurah().observe(this, surahResponse -> {

            Log.d("iii", "onCreate: " + surahResponse.getList().size());

            for (int i = 0; i < surahResponse.getList().size(); i++) {
                list.add(new Surah(surahResponse.getList().get(i).getNumber(),
                        String.valueOf(surahResponse.getList().get(i).getName()),
                        String.valueOf(surahResponse.getList().get(i).getEnglishName()),
                        String.valueOf(surahResponse.getList().get(i).getEnglishNameTranslation()),
                        surahResponse.getList().get(i).getNumberOfAyahs(),
                        String.valueOf(surahResponse.getList().get(i).getRevelationType())

                ));
            }

            if (list.size() != 0) {
                surahAdapter = new SurahAdapter(this, list, this);
                recyclerView.setAdapter(surahAdapter);
                surahAdapter.notifyDataSetChanged();
            }

        });


        ayaText = findViewById(R.id.aya_text);
        ayaTextNo = findViewById(R.id.aya_text_no);

        ayaOfTheDayViewModel = new ViewModelProvider(this).get(AyaOfTheDayViewModel.class);
        ayaOfTheDayViewModel.getAyaOfTheDay(getRandomValue())
                .observe(this, ayaOfTheDay -> {

                    ayaText.setText(ayaOfTheDay.getData().get(0).getText());
                    ayaTextNo.setText(ayaOfTheDay.getData().get(0).getAyaSurah().getName()+" "+ ayaOfTheDay.getData().get(0).getNumberInSurah());
        });
    }

    @Override
    public void onSurahListener(int position) {
        Intent intent = new Intent(MainActivity.this, SurahDetailActivity.class);
        intent.putExtra(Common.SURAH_NO, list.get(position).getNumber());
        intent.putExtra(Common.SURAH_NAME, list.get(position).getName());
        intent.putExtra(Common.SURAH_TOTAL_AYA, list.get(position).getNumberOfAyahs());
        intent.putExtra(Common.SURAH_TYPE, list.get(position).getRevelationType());
        intent.putExtra(Common.SURAH_TRANSLATION,list.get(position).getEnglishNameTranslation());
        startActivity(intent);
    }

    int getRandomValue() {
        Random random = new Random();
        // Generate a random number between 1 and 6237 (inclusive)
        return random.nextInt(6237) + 1;
    }
}