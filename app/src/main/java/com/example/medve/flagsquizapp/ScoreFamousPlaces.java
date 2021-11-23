package com.example.medve.flagsquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.medve.flagsquizapp.Common.CustomAdapter;
import com.example.medve.flagsquizapp.Common.CustomAdapterFamousPlaces;
import com.example.medve.flagsquizapp.DbHelper.DbHelper;
import com.example.medve.flagsquizapp.Model.RankingFamousPlaces;
import com.example.medve.flagsquizapp.Model.RankingFlags;

import java.util.List;

public class ScoreFamousPlaces extends AppCompatActivity {

    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_famous_places);

        lstView = (ListView)findViewById(R.id.lstRankingFamousPlaces);
        DbHelper db = new DbHelper(this);
        List<RankingFamousPlaces> lstRanking = db.getRankingFamousPlaces();
        if (lstRanking.size() > 0)
        {
            CustomAdapterFamousPlaces adapter = new CustomAdapterFamousPlaces(this,lstRanking);
            lstView.setAdapter(adapter);
        }

        ImageView backtostatistic = (ImageView) findViewById(R.id.fromfamousplacestostatistic);
        backtostatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), Statistics.class);
                startActivity(back);

                Intent backanim = new Intent(ScoreFamousPlaces.this,Statistics.class);
                startActivity(backanim);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

            }
        });
    }
}
