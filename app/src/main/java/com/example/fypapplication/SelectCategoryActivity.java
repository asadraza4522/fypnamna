package com.example.fypapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SelectCategoryActivity extends AppCompatActivity {
    String[] type = { "Standard","Premium","Sale"};

    Spinner s1,s2,s3;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
//        s2=findViewById(R.id.spin2);
        s3=findViewById(R.id.spin3);
        next=findViewById(R.id.next);

        ArrayAdapter ap = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        s3.setAdapter(ap);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p= new Intent(SelectCategoryActivity.this,UserDashboard.class);
                startActivity(p);
            }
        });

    }
}