package com.example.fypapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Secuirtytest extends AppCompatActivity {
Button startquiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secuirtytest);
        startquiz=findViewById(R.id.strtquiz);
        startquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sp = new Intent(Secuirtytest.this, QuizActivity.class);
                startActivity(sp);
            }
        });
    }
}