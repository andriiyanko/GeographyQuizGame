package com.example.medve.flagsquizapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.medve.flagsquizapp.DbHelper.DbHelper;
import com.example.medve.flagsquizapp.Model.QuestionFamousPlaces;

import java.util.ArrayList;
import java.util.List;

public class PlayingFamousPlaces extends AppCompatActivity implements View.OnClickListener{

    final static long INTERVAL = 1000;
    final static long TIMEOUT = 7000;
    int progressValue = 0;

    CountDownTimer mCountDown;
    List<QuestionFamousPlaces> questionPlayFamousPlaces = new ArrayList<>(); //total Question
    DbHelper db;
    int index = 0, score = 0, thisQuestion = 0, totalQuestion, correctAnswer;
    String mode = "";

    ProgressBar progressBar;
    ImageView imageView;
    Button btnA, btnB, btnC, btnD;
    TextView txtScore, txtQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_famous_places);

        Bundle extra = getIntent().getExtras();
        if (extra != null)
            mode = extra.getString("MODE");

        db = new DbHelper(this);

        txtScore = (TextView) findViewById(R.id.txtScoreFamousPlaces);
        txtQuestion = (TextView) findViewById(R.id.txtQuestionFamousPlaces);
        progressBar = (ProgressBar) findViewById(R.id.progressBarFamousPlaces);
        imageView = (ImageView) findViewById(R.id.question_famous_places);
        btnA = (Button) findViewById(R.id.btnPlacesAnswerA);
        btnB = (Button) findViewById(R.id.btnPlacesAnswerB);
        btnC = (Button) findViewById(R.id.btnPlacesAnswerC);
        btnD = (Button) findViewById(R.id.btnPlacesAnswerD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        questionPlayFamousPlaces = db.getQuestionFamousPlacesMode(mode);
        totalQuestion = questionPlayFamousPlaces.size();

        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++index);
            }
        };
        showQuestion(index);
    }

    private void showQuestion(int index) {
        if(index < totalQuestion){
            thisQuestion++;
            txtQuestion.setText(String.format("%d/%d", thisQuestion, totalQuestion));
            progressBar.setProgress(0);
            progressValue = 0;

            int ImageId = this.getResources().getIdentifier(questionPlayFamousPlaces.get(index).getImage().toLowerCase(),"drawable",getPackageName());
            imageView.setBackgroundResource(ImageId);
            btnA.setText(questionPlayFamousPlaces.get(index).getAnswerA());
            btnB.setText(questionPlayFamousPlaces.get(index).getAnswerB());
            btnC.setText(questionPlayFamousPlaces.get(index).getAnswerC());
            btnD.setText(questionPlayFamousPlaces.get(index).getAnswerD());

            mCountDown.start();
        }
        else {
            Intent intent = new Intent(this,DoneFamousPlaces.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE",score);
            dataSend.putInt("TOTAL",totalQuestion);
            dataSend.putInt("CORRECT",correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        mCountDown.cancel();
        if (index < totalQuestion){
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals(questionPlayFamousPlaces.get(index).getCorrectAnswer()))
            {
                score+=10;
                correctAnswer++;
                showQuestion(++index);
            }
            else
                showQuestion(++index); //if choose wrong, just go to next question

            txtScore.setText(String.format("%d",score));
        }
    }
}
