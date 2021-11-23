package com.example.medve.flagsquizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.medve.flagsquizapp.Common.Common;
import com.example.medve.flagsquizapp.DbHelper.DbHelper;
import com.example.medve.flagsquizapp.Settings;
import com.example.medve.flagsquizapp.Statistics;

import java.nio.channels.SelectionKey;

public class SelectGameMode extends AppCompatActivity{

    Button btnFlagsmode, btnCitiesmode, btnPlacemode, btnWaterFallsmode,
            btnScoreFlags, btnScoreFamousPlaces, btnScoreCities, btnScoreWaterFalls;
    SeekBar seekbarMode;
    DbHelper db;
    TextView txtMode;
    SharedPreferences sPref;

    final String save_progress_seekbar = "save_progress_seekbar";
    final String save_textmode = "save_textmode";
    final String save_textmode_color = "save_textmode_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_game_mode);


        btnFlagsmode = (Button)findViewById(R.id.flagsmode);
        btnCitiesmode = (Button)findViewById(R.id.citiesmode);
        btnPlacemode = (Button)findViewById(R.id.placemode);
        btnWaterFallsmode = (Button) findViewById(R.id.waterfallsmode);

        View inflatedView = getLayoutInflater().inflate(R.layout.activity_statistics, null);
        btnScoreFlags = (Button) inflatedView.findViewById(R.id.flagsscore);

        btnScoreFamousPlaces = (Button) inflatedView.findViewById(R.id.placescore);

        btnScoreCities = (Button) inflatedView.findViewById(R.id.citiesscore);

        btnScoreWaterFalls = (Button) inflatedView.findViewById(R.id.waterfallsscore);

        /*View inflatedSeekbar = getLayoutInflater().inflate(R.layout.activity_settings, null);
        seekbarMode = (SeekBar) inflatedSeekbar.findViewById(R.id.seekbar);*/

        seekbarMode = (SeekBar) findViewById(R.id.levelSeekbar);
        txtMode = (TextView) findViewById(R.id.levelmode);
        loadSeekBarProgress();

        db = new DbHelper(this);
        try {
            db.createDataBase();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        seekbarMode.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress){
                    case 0:
                        txtMode.setText(Common.MODE.EASY.toString());
                        txtMode.setTextColor(Color.parseColor("#08AE9E"));
                        saveSeekBarProgress();
                        break;
                    case 1:
                        txtMode.setText(Common.MODE.MEDIUM.toString());
                        txtMode.setTextColor(Color.parseColor("#FF9800"));
                        saveSeekBarProgress();
                        break;
                    case 2:
                        txtMode.setText(Common.MODE.HARD.toString());
                        txtMode.setTextColor(Color.MAGENTA);
                        saveSeekBarProgress();
                        break;
                    case 3:
                        txtMode.setText(Common.MODE.HARDEST.toString());
                        txtMode.setTextColor(Color.RED);
                        saveSeekBarProgress();
                        break;
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.flagsmode:
                        Intent intent = new Intent(getApplicationContext(),PlayingFlags.class);
                        intent.putExtra("MODE",getPlayMode()); //Send Mode to playingflags page
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.citiesmode:
                        Intent intentcities = new Intent(getApplicationContext(), PlayingCities.class);
                        intentcities.putExtra("MODE",getPlayMode());
                        startActivity(intentcities);
                        finish();
                        break;
                    case R.id.placemode:
                        Intent intentplaces = new Intent(getApplicationContext(),PlayingFamousPlaces.class);
                        intentplaces.putExtra("MODE",getPlayMode()); //Send Mode to playingplaces page
                        startActivity(intentplaces);
                        finish();
                        break;
                    case R.id.waterfallsmode:
                        Intent intentwaterfalls = new Intent(getApplicationContext(),PlayingWaterFalls.class);
                        intentwaterfalls.putExtra("MODE",getPlayMode()); //Send Mode to playingplaces page
                        startActivity(intentwaterfalls);
                        finish();
                        break;
                }
            }
        };
        btnFlagsmode.setOnClickListener(onClickListener);
        btnPlacemode.setOnClickListener(onClickListener);
        btnCitiesmode.setOnClickListener(onClickListener);
        btnWaterFallsmode.setOnClickListener(onClickListener);


        //go to PlaingFlags
     /*  btnFlagsmode.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),PlayingFlags.class);
               intent.putExtra("MODE",getPlayMode()); //Send Mode to playingflags page
               startActivity(intent);
               finish();

           }
       });*/

        btnScoreFlags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScoreFlags.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView backtomain = (ImageView) findViewById(R.id.backtomain);
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);

                Intent backanim = new Intent(SelectGameMode.this,MainActivity.class);
                startActivity(backanim);
              overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

            }
        });

    }

    private void saveSeekBarProgress() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        int mProgress = seekbarMode.getProgress();

        ed.putInt(save_progress_seekbar,mProgress).commit();
        ed.putString(save_textmode,txtMode.getText().toString()).commit();
        //ed.putString(save_textmode_color,txtMode.getTextColors().toString()).commit();
        ed.putInt(save_textmode_color,txtMode.getCurrentTextColor()).commit();

    }

    private void loadSeekBarProgress() {
        sPref = getPreferences(MODE_PRIVATE);
        //SharedPreferences.Editor ed = sPref.edit();
        int mProgress = sPref.getInt(save_progress_seekbar,0);
        seekbarMode.setProgress(mProgress);

        String savedtext = sPref.getString(save_textmode,"");
        txtMode.setText(savedtext);

        int storedcolor = sPref.getInt(save_textmode_color,0);
        txtMode.setTextColor(storedcolor);
       // ed.putInt(save_progress_seekbar,mProgress).commit();
       // ed.putString(save_progress_seekbar,txtMode.getText().toString());
    }

    private String getPlayMode() {
        if (seekbarMode.getProgress() == 0)
            return Common.MODE.EASY.toString();
        else if (seekbarMode.getProgress() == 1)
            return Common.MODE.MEDIUM.toString();
        else if (seekbarMode.getProgress() == 2)
            return Common.MODE.HARD.toString();
        else
            return Common.MODE.HARDEST.toString();
    }

}
