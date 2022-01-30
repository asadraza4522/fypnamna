
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fypapplication.Company.CompanyLogin;
import com.example.fypapplication.Company.company;
import com.example.fypapplication.Guards.Guard_AddToFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterGuard extends AppCompatActivity implements View.OnClickListener {
    TextView title;
     EditText mailb;
     ImageView namecompany;
     EditText pwdc;
     EditText numbera,name;
     Button registerclick;
     ProgressBar progressBar12;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guard);
        mAuth = FirebaseAuth.getInstance();
namecompany=findViewById(R.id.namecompany);
namecompany.setOnClickListener(this);
        title = (TextView) (findViewById(R.id.guardloginscreen));
        title.setOnClickListener(this);

        registerclick = (Button) (findViewById(R.id.RegisterGuard1));
        registerclick.setOnClickListener(this);

        mailb = (EditText) (findViewById(R.id.email4));
        pwdc = (EditText) (findViewById(R.id.pass3));
        numbera = (EditText) (findViewById(R.id.number3));
         name = findViewById(R.id.name1);


        progressBar12= (ProgressBar) (findViewById(R.id.progress3));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guardloginscreen:
                startActivity(new Intent(this, CompanyLogin.class));
                break;
            case R.id.RegisterGuard1:
                registerguard();

                break;
        }
    }

    private void registerguard() {
        final String mail8 = mailb.getText().toString().trim();
        final String pass8 = pwdc.getText().toString().trim();
        final String name8 = name.getText().toString().trim();
        final String number8 = numbera.getText().toString().trim();



        if (mail8.isEmpty()) {
            mailb.setError("Again enter your email!");
            mailb.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail8).matches()){
            mailb.setError("Again enter your email!");
            mailb.requestFocus();
            return;
        }
        if (pass8.isEmpty()) {
            pwdc.setError("Again enter your password!");
            pwdc.requestFocus();
            return;
        }
        if (pass8.length()<8){
            pwdc.setError("Again enter your password!");
            pwdc.requestFocus();
            return;
        }
        if (name8.isEmpty()){
            name.setError("Enter name!");
            name.requestFocus();
            return;
        }
        if (number8.isEmpty()) {
            numbera.setError("Again enter your number!");
            numbera.requestFocus();
            return;
        }

        progressBar12.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(mail8,pass8).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Guard_AddToFirebase guard_firebase= new Guard_AddToFirebase(mail8,pass8,number8,name8);
                    FirebaseDatabase.getInstance().getReference("Guards").child(name8).setValue(guard_firebase).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterGuard.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                                progressBar12.setVisibility(View.GONE);
                            }
                            else {
                                Toast.makeText(RegisterGuard.this,"Register Failed" +task.getException(),Toast.LENGTH_LONG).show();
                                progressBar12.setVisibility(View.GONE);
                            }
                        }
                    });

                }else{
                    Toast.makeText(RegisterGuard.this,"Register Failed" +task.getException(),Toast.LENGTH_LONG).show();
                    progressBar12.setVisibility(View.GONE);
                }
            }
        });
    }
}