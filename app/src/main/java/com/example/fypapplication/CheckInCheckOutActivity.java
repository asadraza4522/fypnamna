package com.example.fypapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckInCheckOutActivity extends AppCompatActivity {
    Button checkin, checkout, breakstart, breakstop;
    Chronometer workingHoursCount , breakCount;
    int workingCounter = 288000;
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
                workingHoursCount.setBase(workingCounter);
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
        if ((workingHoursCount.getBase()) >= 0) {
            workingHoursCount.setBase(workingHoursCount.getBase() - SystemClock.elapsedRealtime());
        }
        Toast.makeText(this, "Bing! " + workingHoursCount.getBase(), Toast.LENGTH_SHORT).show();
    }
}