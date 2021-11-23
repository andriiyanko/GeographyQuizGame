package com.example.medve.flagsquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.medve.flagsquizapp.DbHelper.DbHelper;

public class DoneFlags extends AppCompatActivity {

    Button btnTryAgain;
    TextView txtFlagsTotalScore, txtFlagsTotalQuestion;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_flags);

        DbHelper db = new DbHelper(this);

        btnTryAgain = (Button)findViewById(R.id.btnTryAgainFlags);
        txtFlagsTotalQuestion = (TextView) findViewById(R.id.txtFlagsTotalQuestion);
        txtFlagsTotalScore = (TextView) findViewById(R.id.txtFlagsTotalScore);
        progressBar = (ProgressBar) findViewById(R.id.doneProgressBarFlags);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SelectGameMode.class);
                startActivity(intent);
                finish();
            }
        });

        //get Data from PlayinFlags
        Bundle extra = getIntent().getExtras();
        if (extra != null){
            int score = extra.getInt("SCORE");
            int totalQuestion = extra.getInt("TOTAL");
            int correctAnswer = extra.getInt("CORRECT");

            //Update
            int playCount = 0;
            if (totalQuestion == 10) //Easy Mode
            {
                playCount = db.getPlayCount(0);
                playCount++;
                db.updatePlayCount(0,playCount); //set PlayCount++
            }
            else if (totalQuestion == 15) //Medium mode
            {
                playCount = db.getPlayCount(1);
                playCount++;
                db.updatePlayCount(1,playCount);
            }
            else if (totalQuestion == 20) //Hard modee
            {
                playCount = db.getPlayCount(2);
                playCount++;
                db.updatePlayCount(2,playCount);
            }
            else if (totalQuestion == 30) //Hard mode
            {
                playCount = db.getPlayCount(3);
                playCount++;
                db.updatePlayCount(3,playCount);
            }

            double substract = ((5.0/(float)score)*100)*(playCount-1); //-1 because playCount++ before calculate result
            double finalScore = score - substract;

            txtFlagsTotalScore.setText(String.format("SCORE : %.1f (-%d)%%",finalScore,5*(playCount-1)));
            txtFlagsTotalQuestion.setText(String.format("PASSED : %d/%d",correctAnswer,totalQuestion));

            progressBar.setMax(totalQuestion);
            progressBar.setProgress(correctAnswer);

            //Insert Score to Database
            db.insertScoreFlags(finalScore);

        }
    }
}
