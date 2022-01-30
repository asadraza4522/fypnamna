package com.example.fypapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class CheckInCheckOutActivity extends AppCompatActivity {
    Button checkin, checkout, breakstart, breakstop;
    Chronometer workingHoursCount , breakCount;
    int workingCounter = 28800;
    int breakCounter = 1800;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_check_out);
        checkin=findViewById(R.id.checkin);
        checkout=findViewById(R.id.checkout);
        breakstart=findViewById(R.id.startbreak);
        breakstop=findViewById(R.id.stopbreak);
        workingHoursCount = (Chronometer) findViewById(R.id.chronometerWorking);
        breakCount = (Chronometer) findViewById(R.id.chronometerBreak);
        workingHoursCount.setBase(SystemClock.elapsedRealtime());

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingHoursCount.start();
            }
        });

        workingHoursCount.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                onChronometerTickHandler();
            }
        });

    }
    private void onChronometerTickHandler()  {
        if(workingCounter < 0) {
            workingCounter = 28800;
        }
        workingCounter--;
    }
}