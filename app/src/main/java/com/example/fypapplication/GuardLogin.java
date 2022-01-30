package com.example.fypapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fypapplication.Company.CompanyDashboard;
import com.example.fypapplication.Company.CompanyRegistration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import me.aflak.libraries.callback.FingerprintDialogCallback;
import me.aflak.libraries.dialog.FingerprintDialog;

public class GuardLogin extends AppCompatActivity implements View.OnClickListener {
    private TextView registerx;
    Button firstall;
    EditText emailc, pswdz;
    ProgressBar progressbar;
    private FirebaseAuth mAuth;

    boolean isbiometricverified=false;

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
        setContentView(R.layout.activity_guard_login);
        registerx=(TextView)findViewById(R.id.register12);
        registerx.setOnClickListener(this);

        firstall=(Button)findViewById(R.id.bll2);
        firstall.setOnClickListener(this);

        emailc=(EditText)findViewById(R.id.email9);
        pswdz=(EditText)findViewById(R.id.password9);
        progressbar=findViewById(R.id.progres);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register12:

                startActivity(new Intent(this, RegisterGuard.class));

                //startActivity(new Intent(this, Secuirtytest.class));
                break;

            case R.id.bll2:

                // replacing email login to fingerprint for testing propose
               // guardLogin();

                if(!isbiometricverified){
                    showfinger();
                }
                else{
                    guardLogin();
                }

                break;
    }
}

    private void guardLogin() {
            String email14=emailc.getText().toString().trim();
            String password14=pswdz.getText().toString().trim();

            if (email14.isEmpty()) {
                emailc.setError(" enter your email!");
                emailc.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email14).matches()){
                emailc.setError("Enter valid email!");
                emailc.requestFocus();
                return;
            }
        if (password14.isEmpty()) {
            pswdz.setError("enter password!");
           pswdz.requestFocus();
            return;
        }
            if (password14.length()<8){
                pswdz.setError("Again enter your password!");
                pswdz.requestFocus();
                return;
            }

            showProgress("loading","please wait");
            mAuth.signInWithEmailAndPassword(email14,password14).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        startActivity(new Intent(GuardLogin.this, GuardDashboard.class));
                        finish();
                    }else{
                        Toast.makeText(GuardLogin.this, "Error!!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                      progressDialog.cancel();
                    }
                }
            });
    }


    // ask faheem if u need help reguarding this script

    void showfinger(){
        FingerprintDialog.initialize(this)
                .title("Biometric Authentication")
                .message("Please place your thumb on fingerprint sensor")
                .callback(new FingerprintDialogCallback() {
                    @Override
                    public void onAuthenticationSucceeded() {

                        //get this password later from shareprefernces
                        pswdz.setText("1234sara2");
                        guardLogin();
                        // save guard login details in shareprefernces
                        // and  on succesfull of fingerprint verification
                        // launch user dashboard instead of toast msg
                        Toast.makeText(getApplicationContext(),"verified succesfully",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onAuthenticationCancel() {
                        //  do something on verification failed
                        isbiometricverified=true;
                        Toast.makeText(getApplicationContext(),"please try again",Toast.LENGTH_SHORT).show();

                    }
                })
            .show();
    }



}