package com.example.simpletimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView TextTime;
    Button buttonGo;
    Boolean CountisActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        TextTime.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        seekBar.setEnabled(true);
        CountisActive = false;
        buttonGo.setText("Go");
    }
    public void updateTime(int secLeft){
        int min = secLeft/60;
        int sec =  secLeft-min*60;
        String secString = Integer.toString(sec);
        String minString = Integer.toString(min);
        if (sec <= 9){
            secString = "0" + secString;
        }

        TextTime.setText(minString+ ":" + secString);

    }
    public void Timer(View view){

        if (CountisActive == false) {
            buttonGo.setText("stop");
            CountisActive = true;
            seekBar.setEnabled(false);
            countDownTimer =new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {

                    updateTime((int) l / 1000);


                }

                @Override
                public void onFinish() {
                    TextTime.setText("0:00");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sirin);
                    mediaPlayer.start();
                    Log.i("finied", "yup");
                }
            }.start();

        }
        else {
            resetTimer();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        TextTime = (TextView)findViewById(R.id.TextTime);
        buttonGo = (Button)findViewById(R.id.buttonG0);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        TextTime.setEnabled(false);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTime(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}