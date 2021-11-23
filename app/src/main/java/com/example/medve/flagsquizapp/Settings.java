package com.example.medve.flagsquizapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.LinkAddress;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medve.flagsquizapp.Common.Common;

public class Settings extends AppCompatActivity {

    private Context context;

    ImageView backtomain;
    Button btnSound;
    Button btnNotification;
    SharedPreferences sPref;
    /*SeekBar seekBar;
    TextView txtMode;*/

    Toast toast;
    AudioManager audioManager;
    NotificationManager notificationManager;

    //Button btnPlay, btnStatistics, btnSettings;

    final String save_text_sound = "save_text_sound";
    final String save_text_notif = "save_text_notif";
    final String save_progress_seekbar = "save_progress_seekbar";
    final String save_textmode = "save_textmode";
    final String save_textmode_color = "save_textmode_color";

    private final int NOTIFICATION_ID = 127;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



        btnSound = (Button) findViewById(R.id.sound);
        btnNotification = (Button) findViewById(R.id.notification);
        /*seekBar = (SeekBar) findViewById(R.id.seekbar);
        txtMode = (TextView) findViewById(R.id.textMode);*/
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        loadSoundText();
        loadNotificationText();
       // loadSeekBarProgress();

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sound:
                        changeStatusSound();
                        saveSoundText();
                        break;
                    case R.id.notification:
                        if (btnNotification.getText().toString() == "Notifications off") {
                            btnNotification.setText("Notifications on");
                            showNotification();
                            showToast();
                        }
                        else
                        {
                            btnNotification.setText("Notifications off");
                        }
                        saveNotificationText();
                        break;
                }
            }
        };


        btnSound.setOnClickListener(onClickListener);
        btnNotification.setOnClickListener(onClickListener);

        //animation for back button
        backtomain = (ImageView) findViewById(R.id.backtomain);
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);

                Intent backanim = new Intent(Settings.this,MainActivity.class);
                startActivity(backanim);
                overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right);
            }
        });

       /* //Event for seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
               *//* if (progress == 0)
                    txtMode.setText(Common.MODE.EASY.toString());

                else if (progress == 1)
                    txtMode.setText(Common.MODE.MEDIUM.toString());
                else if (progress == 2)
                    txtMode.setText(Common.MODE.HARD.toString());
                else if (progress == 3)
                    txtMode.setText(Common.MODE.HARDEST.toString());*//*
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

    }

    //On/Off Sound of App
    private void changeStatusSound() {

        if (btnSound.getText().toString() == "Sound off") {
            btnSound.setText("Sound on");
            audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
        }
        else
        {
            btnSound.setText("Sound off");
            AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        }

    }

    //Show Notification
    private void showNotification(){

        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store"));
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplication().getResources(),R.mipmap.ic_launcher))
                .setTicker("Available new updates of the app")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Notification")
                .setContentText("Click to go to Play Market");

        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    //Show Toast
    private void showToast(){
        toast = Toast.makeText(Settings.this, "Available new updates", Toast.LENGTH_LONG);
        toast.show();
    }

    /*private void saveSeekBarProgress() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        int mProgress = seekBar.getProgress();

        ed.putInt(save_progress_seekbar,mProgress).commit();
        ed.putString(save_textmode,txtMode.getText().toString()).commit();
        //ed.putString(save_textmode_color,txtMode.getTextColors().toString()).commit();
        ed.putInt(save_textmode_color,txtMode.getCurrentTextColor()).commit();

    }*/

   /* private void loadSeekBarProgress() {
        sPref = getPreferences(MODE_PRIVATE);
        //SharedPreferences.Editor ed = sPref.edit();
        int mProgress = sPref.getInt(save_progress_seekbar,0);
        seekBar.setProgress(mProgress);

        String savedtext = sPref.getString(save_textmode,"");
        txtMode.setText(savedtext);

        int storedcolor = sPref.getInt(save_textmode_color,0);
        txtMode.setTextColor(storedcolor);
       *//* ed.putInt(save_text_seekbar,mProgress).commit();
        ed.putString(save_text_seekbar,txtMode.getText().toString());*//*
    }*/


    private void saveNotificationText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(save_text_notif,btnNotification.getText().toString());
        ed.commit();
    }

    private void loadNotificationText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedtext = sPref.getString(save_text_notif,"");
        btnNotification.setText(savedtext);
    }


    private void saveSoundText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(save_text_sound,btnSound.getText().toString());
        ed.commit();
    }

    private void loadSoundText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedtext = sPref.getString(save_text_sound,"");
        btnSound.setText(savedtext);
    }

/*    @Override
    protected void onPause() {
        super.onPause();
        saveSeekBarProgress();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveSeekBarProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveSeekBarProgress();
    }*/
    /* @Override
    protected void onDestroy() {
        super.onDestroy();
        saveSoundText();
        saveNotificationText();
    }*/

}
