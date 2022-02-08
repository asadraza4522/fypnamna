package com.example.fypapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerOptionActivity extends AppCompatActivity {
    Button viewGuardActivity , trackGuardLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_option);
        viewGuardActivity =  findViewById(R.id.showGuardShift);
        trackGuardLocation = findViewById(R.id.trackLocation);

        viewGuardActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ShiftRecordActivity.class);
                startActivity(i);
            }
        });
        trackGuardLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(i);
            }
        });
    }
}