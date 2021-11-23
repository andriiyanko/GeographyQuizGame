package com.example.medve.flagsquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.medve.flagsquizapp.Common.CustomAdapterCities;
import com.example.medve.flagsquizapp.Common.CustomAdapterWaterFalls;
import com.example.medve.flagsquizapp.DbHelper.DbHelper;
import com.example.medve.flagsquizapp.Model.RankingCities;
import com.example.medve.flagsquizapp.Model.RankingWaterFalls;

import java.util.List;

public class ScoreWaterFalls extends AppCompatActivity {

    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_water_falls);

        lstView = (ListView)findViewById(R.id.lstRankingWaterFalls);
        DbHelper db = new DbHelper(this);
        List<RankingWaterFalls> lstRanking = db.getRankingWaterFalls();
        if (lstRanking.size() > 0)
        {
            CustomAdapterWaterFalls adapter = new CustomAdapterWaterFalls(this,lstRanking);
            lstView.setAdapter(adapter);
        }

        ImageView backtostatistic = (ImageView) findViewById(R.id.fromwaterfallstostatistic);
        backtostatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), Statistics.class);
                startActivity(back);

                Intent backanim = new Intent(ScoreWaterFalls.this,Statistics.class);
                startActivity(backanim);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

            }
        });
    }
}
