package com.example.medve.flagsquizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.medve.flagsquizapp.DbHelper.DbHelper;

public class MainActivity extends AppCompatActivity {

    private Context context;

    Button btnplay;
    Button btnsettings;
    Button btnstatistics;
   // Button signout;
    DbHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnplay = (Button) findViewById(R.id.btnplay);
        btnsettings = (Button) findViewById(R.id.btnsettings);
        btnstatistics = (Button) findViewById(R.id.btnstatistic);

      /*  db = new DbHelper(this);
        try {
            db.createDataBase();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/

        // ChangeMainSound();

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnplay:
                        Intent movetogamemode = new Intent(getApplicationContext(), SelectGameMode.class);
                        startActivity(movetogamemode);
                        break;
                    case R.id.btnsettings:
                        Intent movetosettings = new Intent(getApplicationContext(), Settings.class);
                        startActivity(movetosettings);
                        break;
                    case R.id.btnstatistic:
                        Intent movetostatistic = new Intent(getApplicationContext(),Statistics.class);
                        startActivity(movetostatistic);
                        break;
                }
            }
        };

        btnplay.setOnClickListener(onClickListener);
        btnsettings.setOnClickListener(onClickListener);
        btnstatistics.setOnClickListener(onClickListener);


        /*Button play = (Button) findViewById(R.id.btnsignout);
        play.setVisibility(View.GONE);
      //  play.setText("Play");*/
    }

}
