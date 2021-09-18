package com.userpanel.friend.alquranapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.userpanel.friend.alquranapp.R;
import com.userpanel.friend.alquranapp.adapter.SurahDetailAdapter;
import com.userpanel.friend.alquranapp.common.Common;
import com.userpanel.friend.alquranapp.model.SurahDetail;
import com.userpanel.friend.alquranapp.viewmodel.SurahDetailViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurahDetailActivity extends AppCompatActivity {

    private TextView surahName,surahType,surahTranslation;
    private int no;
    private RecyclerView recyclerView;
    private List<SurahDetail> list;
    private SurahDetailAdapter adapter;
    private SurahDetailViewModel surahDetailViewModel;
    private String urdu = "urdu_junagarhi";
    private String hindi = "hindi_omari";
    private String english = "english_hilali_khan";

    private EditText searchView;
    private ImageButton settingButton;

    private RadioGroup radioGroup,audio_group;
    private RadioButton translationButton , qariSelectButton;
    private String lan;
    
    private String qariAB = "abdul_basit_murattal";
    private String qariAW = "abdul_wadood_haneef_rare";
    private String qr;
    Handler handler = new Handler();
    SeekBar seekBar;
    TextView startTime , totalTime;
    ImageButton playButton;
    MediaPlayer mediaPlayer;
    private String str;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah_detail);

        init();

        no = getIntent().getIntExtra(Common.SURAH_NO,0);
        surahName.setText(getIntent().getStringExtra(Common.SURAH_NAME));

        surahType.setText(getIntent().getStringExtra(Common.SURAH_TYPE)+" "+
                getIntent().getIntExtra(Common.SURAH_TOTAL_AYA,0)+" AYA");

        surahTranslation.setText(getIntent().getStringExtra(Common.SURAH_TRANSLATION));

        recyclerView.setHasFixedSize(true);
        list = new ArrayList<>();

        surahTranslation(urdu,no);

        try {
            listenAudio(qariAB);
        } catch (IOException e) {
            e.printStackTrace();
        }
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SurahDetailActivity.this,
                        R.style.BottomSheetDialogTheme);

                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View view = inflater.inflate(R.layout.bottom_sheet_layout,
                        findViewById(R.id.sheetContainer));

                view.findViewById(R.id.save_settings_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        radioGroup = view.findViewById(R.id.translation_group);
                        audio_group = view.findViewById(R.id.audio_group);

                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        translationButton = view.findViewById(selectedId);
                        if(selectedId==-1){
                            Toast.makeText(SurahDetailActivity.this, "nothing selected", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(SurahDetailActivity.this, "selected", Toast.LENGTH_SHORT).show();
                        }
                        if(translationButton.getText().toString().toLowerCase().trim().equals("urdu")){
                            lan = urdu;
                        }else if(translationButton.getText().toString().toLowerCase().trim().equals("hindi")){
                            lan = hindi;
                        }else if(translationButton.getText().toString().toLowerCase().trim().equals("english")){
                            lan = english;
                        }

                        surahTranslation(lan,no);

                        int id = audio_group.getCheckedRadioButtonId();
                        qariSelectButton = view.findViewById(id);
                        if(qariSelectButton.getText().toString().trim().toLowerCase().equals("abdul basit murattal")){
                            qr = qariAB;
                        }else if(qariSelectButton.getText().toString().trim().toLowerCase().equals("abdul wadood haneef rare")){
                            qr = qariAW;
                        }
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        try {
                            listenAudio(qr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
            }
        });

    }


    private void init(){
        surahName = findViewById(R.id.surah_name);
        surahType = findViewById(R.id.type);
        surahTranslation = findViewById(R.id.translation);
        recyclerView = findViewById(R.id.surah_detail_rv);
        searchView = findViewById(R.id.search_view);
        settingButton = findViewById(R.id.settings_button);

    }

    private void surahTranslation(String lan, int id) {

        if(list.size()>0){
            list.clear();
        }

        surahDetailViewModel = new ViewModelProvider(this).get(SurahDetailViewModel.class);
        surahDetailViewModel.getSurahDetail(lan,id).observe(this, surahDetailResponse -> {


            for (int i=0;i<surahDetailResponse.getList().size();i++){
                list.add(new SurahDetail(surahDetailResponse.getList().get(i).getId(),
                        surahDetailResponse.getList().get(i).getSura(),
                        surahDetailResponse.getList().get(i).getAya(),
                        surahDetailResponse.getList().get(i).getArabic_text(),
                        surahDetailResponse.getList().get(i).getTranslation(),
                        surahDetailResponse.getList().get(i).getFootnotes()));
            }

            if(list.size()!=0){
                adapter = new SurahDetailAdapter(this,list);
                recyclerView.setAdapter(adapter);
            }

        });
    }

    private void filter(String id) {
        ArrayList<SurahDetail> arrayList = new ArrayList<>();
        for(SurahDetail detail : list){
            if(String.valueOf(detail.getId()).contains(id)){
                arrayList.add(detail);
            }
        }
        adapter.filter(arrayList);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void listenAudio(String qari) throws IOException {

        playButton = findViewById(R.id.play_button);
        startTime = findViewById(R.id.start_time);
        totalTime = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seekBar);

        mediaPlayer = new MediaPlayer();
        seekBar.setMax(100);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                }else{
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.ic_baseline_pause_circle_24);
                    updateSeekBar();
                }
            }
        });

        preparedMediaPlayer(qari);

        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               SeekBar seekBar = (SeekBar) v;
               int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
               mediaPlayer.seekTo(playPosition);
               startTime.setText(timeToMilliSecond(mediaPlayer.getCurrentPosition()));
               return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seekBar.setProgress(0);
                playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                startTime.setText("0:00");
                totalTime.setText("0:00");
                mediaPlayer.reset();
                try {
                    preparedMediaPlayer(qari);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void preparedMediaPlayer(String qari) throws IOException {

        if(no<10){
            str = "00"+no;
        }else if(no<100){
            str = "0"+no;
        }else if(no>=100){
            str = String.valueOf(no);
        }
        //https://download.quranicaudio.com/quran/abdul_wadood_haneef_rare/001.mp3
        mediaPlayer.setDataSource("https://download.quranicaudio.com/quran/"+qari+"/"+str.trim()+".mp3");
        mediaPlayer.prepare();
        totalTime.setText(timeToMilliSecond(mediaPlayer.getDuration()));
    }


    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long curentDuration = mediaPlayer.getCurrentPosition();
            startTime.setText(timeToMilliSecond(curentDuration));
        }
    };

    private void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) *100));
            handler.postDelayed(updater,1000);
        }
    }

    private String timeToMilliSecond(long milliSecond){
        String timerString = "";
        String secondString ;

        int hours = (int) (milliSecond /(1000 * 60 * 60));
        int minutes = (int) (milliSecond % (1000 * 60 * 60)) / (1000 * 60);
        int second = (int) ((milliSecond % (1000 * 60 * 60))% (1000 * 60) /1000);

        if(hours>0){
            timerString = hours + ":";
        }
        if(second < 10){
            secondString = "0" + second;
        }else{
            secondString = "" + second;
        }
        timerString = timerString + minutes + ":" + secondString;
        return timerString;
    }


    @Override
    protected void onDestroy() {
        if(mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        if(mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onStop();
    }

    @Override
    protected void onPause() {
        if(mediaPlayer.isPlaying()) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
        }
        super.onPause();
    }
}