package com.example.fypapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fypapplication.Admin.AdminLogin;
import com.example.fypapplication.Company.CompanyLogin;
import com.example.fypapplication.Post.model.Post;

public class UsersActivity extends AppCompatActivity {
Button b1, b2, b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        b1=findViewById(R.id.user);
        b2=findViewById(R.id.company);
        b3=findViewById(R.id.admin);
        b4=findViewById(R.id.registerguard);

        Post post=new Post();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsersActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsersActivity.this, CompanyLogin.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsersActivity.this, AdminLogin.class);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent(UsersActivity.this, GuardLogin.class);
                startActivity(p);
            }
        });
    }
}