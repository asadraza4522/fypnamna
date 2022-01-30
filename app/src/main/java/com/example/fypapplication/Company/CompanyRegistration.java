
package com.example.fypapplication.Company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.fypapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CompanyRegistration extends AppCompatActivity implements View.OnClickListener {
    private TextView  register;
    private EditText mail1, pwd, number,ntn,username;
    private Button reg1;
    private ImageView title;
    private ProgressBar progressBar1;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);
        mAuth = FirebaseAuth.getInstance();

        title = findViewById(R.id.namecompany1);
        title.setOnClickListener(this);

        register = (Button) (findViewById(R.id.RegisterMember1));
        register.setOnClickListener(this);

        mail1 = (EditText) (findViewById(R.id.email2));
        pwd = (EditText) (findViewById(R.id.pass1));
        number = (EditText) (findViewById(R.id.number1));
        ntn=findViewById(R.id.ntn);
        username = (EditText) (findViewById(R.id.fullname));

        progressBar1 = (ProgressBar) (findViewById(R.id.progress1));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.namecompany1:
                startActivity(new Intent(this, CompanyLogin.class));
                break;
            case R.id.RegisterMember1:
                register();
                break;
        }
    }

    private void register() {
        final String mail2 = mail1.getText().toString().trim();
        final String pass1 = pwd.getText().toString().trim();
        final String ntn1 = ntn.getText().toString().trim();
        final String number1 = number.getText().toString().trim();
        final String username1 = username.getText().toString().trim();

        if (username1.isEmpty()) {
            username.setError("enter your name!");
            username.requestFocus();
            return;
        }
        if (mail2.isEmpty()) {
            mail1.setError("Again enter your email!");
            mail1.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mail2).matches()){
            mail1.setError("Again enter your email!");
            mail1.requestFocus();
            return;
        }
        if (pass1.isEmpty()) {
            pwd.setError("Again enter your password!");
            pwd.requestFocus();
            return;
        }
        if (pass1.length()<8){
            pwd.setError("Again enter your password!");
            pwd.requestFocus();
            return;
        }
        if (ntn1.length()<7){
            ntn.setError("NTN no is incomplete!");
            ntn.requestFocus();
            return;
        }
        if (number1.isEmpty()) {
            number.setError("Again enter your number!");
            number.requestFocus();
            return;
        }

        progressBar1.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(mail2,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Company_ToFirebase company= new Company_ToFirebase(mail2,pass1,ntn1);
                    FirebaseDatabase.getInstance().getReference("company").child(username1).setValue(company).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(CompanyRegistration.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                                progressBar1.setVisibility(View.GONE);
                            }
                            else {

                                Toast.makeText(CompanyRegistration.this,"Register Failed" +task.getException(),Toast.LENGTH_LONG).show();
                                progressBar1.setVisibility(View.GONE);
                            }
                        }
                    });

                }else{
                    Toast.makeText(CompanyRegistration.this,"Register Failed" +task.getException(),Toast.LENGTH_LONG).show();
                    progressBar1.setVisibility(View.GONE);

                }
            }
        });
    }
}