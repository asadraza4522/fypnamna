package com.example.fypapplication.Company;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.fypapplication.R;

import java.util.Calendar;

public class CreateScheduleActivity extends AppCompatActivity {
    EditText editTextItem;
    EditText editTextModel;
    EditText editTextSerial;
    EditText editTextID;
    Button createButton,selectdate,selecttime;
    private static final String TAG = "CreateScheduleActivity";

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        editTextItem = (EditText)findViewById(R.id.editTextItem);
        editTextModel = (EditText)findViewById(R.id.editTextModel);
        editTextSerial = (EditText)findViewById(R.id.editTextSerial);
        editTextID = (EditText)findViewById(R.id.editTextID);
        selectdate=findViewById(R.id.selectdate);
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(CreateScheduleActivity.this,android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: date:"+month+"/"+dayOfMonth+"/"+year);
                String date = month + "/" + dayOfMonth + "/" + year;
                editTextModel.setText(date);
            }
        };
        selecttime=findViewById(R.id.selecttime);
        selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
               int  mHour = c.get(Calendar.HOUR_OF_DAY);
               int  mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateScheduleActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                editTextSerial.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = "";
                item = "Customer Name:  "+editTextItem.getText().toString().trim() +"             "+
                       "Day Of Meeting:  "+ editTextModel.getText().toString().trim() +"                      "+
                        "Time Of Meeting:  "+editTextSerial.getText().toString().trim() +"    "+
                        "Location:    "+editTextID.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                intent.putExtra("data", item);
                startActivity(intent);
            }
        });
    }
}