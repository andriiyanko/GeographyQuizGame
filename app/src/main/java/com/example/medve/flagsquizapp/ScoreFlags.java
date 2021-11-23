package com.example.medve.flagsquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.medve.flagsquizapp.Common.CustomAdapter;
import com.example.medve.flagsquizapp.DbHelper.DbHelper;
import com.example.medve.flagsquizapp.Model.RankingFlags;

import java.util.List;

public class ScoreFlags extends AppCompatActivity {

    ListView lstView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_flags);

        lstView = (ListView)findViewById(R.id.lstRanking);
        DbHelper db = new DbHelper(this);
        List<RankingFlags> lstRanking = db.getRankingFlags();
        if (lstRanking.size() > 0)
        {
            CustomAdapter adapter = new CustomAdapter(this,lstRanking);
            lstView.setAdapter(adapter);
        }

        ImageView backtostatistic = (ImageView) findViewById(R.id.fromflagstostatistic);
        backtostatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), Statistics.class);
                startActivity(back);

                Intent backanim = new Intent(ScoreFlags.this,Statistics.class);
                startActivity(backanim);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);

            }
        });
    }
}
