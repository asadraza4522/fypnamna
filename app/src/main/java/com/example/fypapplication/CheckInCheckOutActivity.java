package com.example.fypapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CheckInCheckOutActivity extends AppCompatActivity {
    private static final String TAG = "Check Out Activity";
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
//        workingHoursCount.setBase(SystemClock.elapsedRealtime());
        if(savedInstanceState != null || getDate().equals(new Date().toString())){
            long saveValue = savedInstanceState.getLong("ChronoTime");
            Log.d(TAG, "onCreate: save time : " + saveValue  );
            workingHoursCount.setBase(saveValue);
            workingHoursCount.start();
        }

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. parse your input as a date object.
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date startDate = null;
                try {
                    startDate = format.parse("08:00:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //2. feed it to a Calendar Object
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);

                //3. get the hour, minute, second variable
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                Log.d(TAG, "onClick: " + workingHoursCount.getBase());
                workingHoursCount.setBase(SystemClock.elapsedRealtime() + (hour * 60000 * 60 + minute * 60000 + second * 1000));
                workingHoursCount.start();
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingHoursCount.stop();
                Log.d(TAG, "onClick: " + workingHoursCount.getBase());
            }
        });
    }
    @Override
    public void onSaveInstanceState (@NotNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("ChronoTime", workingHoursCount.getBase());
    }

    private void saveCount( long currentCount) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("countTime",currentCount);
        editor.putString("currentDate",currentDate);
        editor.apply();
    }
    private long getCount() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        return pref.getLong("countTime",0);
    }private String getDate() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        return pref.getString("currentDate",null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveCount(workingHoursCount.getBase());
    }

    @Override
    protected void onResume() {
        super.onResume();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        if(getDate().equals(currentDate)){
            Log.d(TAG, "onCreate:  get Count : " + getCount() );
            workingHoursCount.setBase(getCount());
            workingHoursCount.start();
        }
    }

}