package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long PauseOffSet = 0;
    private boolean isPlaying = false;
    private ToggleButton toggleButton;
    private Button reset_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        toggleButton = findViewById(R.id.Toggle);
        reset_btn = findViewById(R.id.reset_btn);

        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    chronometer.setBase(SystemClock.elapsedRealtime()-PauseOffSet);
                    chronometer.start();
                    isPlaying = true;
                }else{
                    chronometer.stop();
                    PauseOffSet = SystemClock.elapsedRealtime()- chronometer.getBase();
                    isPlaying = false;
                }
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPlaying){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    PauseOffSet = 0;
                    chronometer.start();
                    isPlaying= true;
                }
            }
        });


    }
}