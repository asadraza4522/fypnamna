package com.example.fypapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShiftRecordActivity extends AppCompatActivity {
    EditText guardname, shiftstarttime,shiftendtime,totalbreaktime,noofshifthour;
    TextView editbreaktime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_record);
        guardname= findViewById(R.id.guardName);
        shiftstarttime=findViewById(R.id.shiftStart);
        shiftendtime=findViewById(R.id.shiftEnd);
        totalbreaktime=findViewById(R.id.breakTiming);
        editbreaktime=findViewById(R.id.editbreaktime);
        noofshifthour=findViewById(R.id.shiftHours);

        guardname.setText("Guard 1");
        shiftstarttime.setText("08:00am");
        shiftendtime.setText("06:00pm");
        totalbreaktime.setText("30min");
        noofshifthour.setText("10h");
    }
}