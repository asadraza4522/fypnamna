package com.example.fypapplication.Company;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fypapplication.R;

import java.util.Calendar;

public class GuardRequirment extends AppCompatActivity {
EditText start, end,comments, days, namecompany,contact,address,required_guard;
Button takestart, takeend,sendRequest,cancel;
    SharedPreferences sharedpreferences;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String addres = "addressKey";
    public static final String started = "startKey";
    public static final String ended = "endKey";
    public static final String describe = "describeKey";
    public static final String guards = "guardKey";
    public static final String day = "dayKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_requirment);
start=findViewById(R.id.timestart);
end=findViewById(R.id.timeend);
comments=findViewById(R.id.comments);
days=findViewById(R.id.daysentry);
namecompany=findViewById(R.id.company_name);
contact=findViewById(R.id.companycontact);
address=findViewById(R.id.address);
required_guard=findViewById(R.id.requiredguard);
sendRequest=findViewById(R.id.sendrequest);
cancel=findViewById(R.id.cancel);
takestart=findViewById(R.id.takestarttime);
takeend=findViewById(R.id.takeendtime);
takestart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(GuardRequirment.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                start.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
});
takeend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog nTimePicker;
        nTimePicker = new TimePickerDialog(GuardRequirment.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                end.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, false);//Yes 24 hour time
        nTimePicker.setTitle("Select Time");
        nTimePicker.show();
    }
});

sendRequest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sendRequest.setText("Request Send");
        String starttime=start.getText().toString();
        String endtime=end.getText().toString();
        String requestcomments = comments.getText().toString();
        String requireddays = days.getText().toString();
        String nameofcompany = namecompany.getText().toString();
        String companycontact = contact.getText().toString();
        String companyaddress= address.getText().toString();
        String requiredguards=required_guard.getText().toString();
        SharedPreferences preferences = getSharedPreferences("temp", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(started,starttime);
        editor.putString(ended,endtime);
        editor.putString(day,requireddays);
        editor.putString(Name,nameofcompany);
        editor.putString(Phone,companycontact);
        editor.putString(addres,companyaddress);
        editor.putString(describe,requestcomments);
        editor.putString(guards,requiredguards);
        editor.commit();
        //Bundle bundle=new Bundle();
       // bundle.putString("message",starttime);
       // bundle.putString("message0",endtime);
       // bundle.putString("message1",requireddays);
       // bundle.putString("message2",nameofcompany);
        //bundle.putString("message3",companycontact);
        //bundle.putString("message4",companyaddress);
       // bundle.putString("message5",requestcomments);
        //bundle.putString("message6",requiredguards);
        //Intent intent=new Intent();
        //intent.putExtras(bundle);
        Toast.makeText(GuardRequirment.this, "Details Sended", Toast.LENGTH_SHORT).show();

    }
});
    }
}