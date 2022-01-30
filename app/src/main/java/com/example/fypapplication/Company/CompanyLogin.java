package com.example.fypapplication.Company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fypapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CompanyLogin extends AppCompatActivity implements View.OnClickListener {
    private TextView register1;
    Button first;
    EditText email2, pswd1;

    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    void showProgress(String title,String msg){
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);
        register1=(TextView)findViewById(R.id.register1);
        register1.setOnClickListener(this);

        first=(Button)findViewById(R.id.bt2);
        first.setOnClickListener(this);

        email2=(EditText)findViewById(R.id.email1);
        pswd1=(EditText)findViewById(R.id.password1);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register1:
                startActivity(new Intent(this, CompanyRegistration.class));
                break;

            case R.id.bt2:
                companyLogin();
                break;
    }
}

    private void companyLogin() {
            String email1=email2.getText().toString().trim();
            String password1=pswd1.getText().toString().trim();

            if (email1.isEmpty()) {
                email2.setError("Again enter your email!");
                email2.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
                email2.setError("Enter valid email!");
                email2.requestFocus();
                return;
            }

            if (password1.length()<8){
                pswd1.setError("Again enter your password!");
                pswd1.requestFocus();
                return;
            }
        showProgress("Signing In","please wait");
            mAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        progressDialog.cancel();
                        startActivity(new Intent(CompanyLogin.this, CompanyDashboard.class));
                    }else{
                        progressDialog.cancel();
                        Toast.makeText(CompanyLogin.this, "Error!!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
    }