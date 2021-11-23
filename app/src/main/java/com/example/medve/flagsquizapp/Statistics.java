package com.example.medve.flagsquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Statistics extends AppCompatActivity {


    Button btnFlagsScore, btnCitiesScore, btnPlaceScore,btnWaterFallsScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        btnFlagsScore = (Button)findViewById(R.id.flagsscore);
        btnCitiesScore = (Button)findViewById(R.id.citiesscore);
        btnPlaceScore = (Button)findViewById(R.id.placescore);
        btnWaterFallsScore = (Button)findViewById(R.id.waterfallsscore);

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.flagsscore:
                        Intent intent = new Intent(getApplicationContext(),ScoreFlags.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.citiesscore:
                        Intent movetoCities = new Intent(getApplicationContext(),ScoreCities.class);
                        startActivity(movetoCities);
                        finish();
                        break;
                    case R.id.placescore:
                        Intent movetoPlaces = new Intent(getApplicationContext(),ScoreFamousPlaces.class);
                        startActivity(movetoPlaces);
                        break;
                    case R.id.waterfallsscore:
                        Intent movetoWaterFalls = new Intent(getApplicationContext(),ScoreWaterFalls.class);
                        startActivity(movetoWaterFalls);
                        break;
                }
            }
        };
        btnFlagsScore.setOnClickListener(onClickListener);
        btnPlaceScore.setOnClickListener(onClickListener);
        btnCitiesScore.setOnClickListener(onClickListener);
        btnWaterFallsScore.setOnClickListener(onClickListener);


        ImageView backtomain = (ImageView) findViewById(R.id.backtomain);
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);

                Intent backanim = new Intent(Statistics.this,MainActivity.class);
                startActivity(backanim);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

            }
        });
    }
}
