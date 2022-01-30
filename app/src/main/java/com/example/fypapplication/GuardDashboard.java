package com.example.fypapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuardDashboard extends AppCompatActivity {
Button checkincheckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_dashboard);
        checkincheckout=findViewById(R.id.checkincheckout);
checkincheckout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(GuardDashboard.this,CheckInCheckOutActivity.class);
        startActivity(i);
    }
});
    }
}