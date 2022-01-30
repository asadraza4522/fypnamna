package com.example.fypapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Registeruser extends AppCompatActivity implements View.OnClickListener {

    private TextView  regist;
    private ImageView tit;
    private EditText gmail, password, numb,username;
    private Button but;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);
//        setSupportActionBar((androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar45));
        Toolbar toolbar=findViewById(R.id.toolbar45);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        tit =  findViewById(R.id.namecompany);
        tit.setOnClickListener(this);

        regist = (Button) (findViewById(R.id.RegisterMember));
        regist.setOnClickListener(this);

        gmail = (EditText) (findViewById(R.id.mail));
        password = (EditText) (findViewById(R.id.pass));
        numb = (EditText) (findViewById(R.id.number));
        username = (EditText) (findViewById(R.id.fullname));

        progressBar = (ProgressBar) (findViewById(R.id.progressb));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.namecompany:  //returns back
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.RegisterMember:
                regist();
                break;
        }
    }

    private void regist() {
        final String mail = gmail.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        final String number = numb.getText().toString().trim();


        final String username1 = username.getText().toString().trim();


        if (username1.isEmpty()) {
            username.setError(" enter your name!");
            username.requestFocus();
            return;
        }
        if (mail.isEmpty()) {
            gmail.setError("Again enter your email!");
            gmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            gmail.setError("Again enter your email!");
            gmail.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("Again enter your password!");
            password.requestFocus();
            return;
        }
        if (pass.length() < 8) {
            password.setError("Again enter your password!");
            password.requestFocus();
            return;
        }
        if (number.isEmpty()) {
            numb.setError("Again enter your number!");
            numb.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(mail, pass, number);
                    FirebaseDatabase.getInstance().getReference("users").child(username1).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Registeruser.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(Registeruser.this, "Register Failed" + task.getException(), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                } else {
                    Toast.makeText(Registeruser.this, "Register Failed" + task.getException(), Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}




