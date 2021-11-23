package com.example.medve.flagsquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.medve.flagsquizapp.Common.CustomAdapterCities;
import com.example.medve.flagsquizapp.Common.CustomAdapterFamousPlaces;
import com.example.medve.flagsquizapp.DbHelper.DbHelper;
import com.example.medve.flagsquizapp.Model.RankingCities;
import com.example.medve.flagsquizapp.Model.RankingFamousPlaces;

import java.util.List;

public class ScoreCities extends AppCompatActivity {

    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_cities);

        lstView = (ListView)findViewById(R.id.lstRankingCities);
        DbHelper db = new DbHelper(this);
        List<RankingCities> lstRanking = db.getRankingCities();
        if (lstRanking.size() > 0)
        {
            CustomAdapterCities adapter = new CustomAdapterCities(this,lstRanking);
            lstView.setAdapter(adapter);
        }

        ImageView backtostatistic = (ImageView) findViewById(R.id.fromcitiestostatistic);
        backtostatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), Statistics.class);
                startActivity(back);

                Intent backanim = new Intent(ScoreCities.this,Statistics.class);
                startActivity(backanim);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

            }
        });
    }
}
