package com.example.fypapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowScoreActivity extends AppCompatActivity  {
    TextView TxtScore;
    TextView TxtStatus;
    MediaPlayer audio;
    Button btn;
    private ImageView tit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);
        TxtScore = findViewById(R.id.txtscore);
        TxtStatus = findViewById(R.id.txtStatus);
//        btn=findViewById(R.id.loginbtn);
        Intent intent = getIntent();
        String scores = String.valueOf(intent.getIntExtra("score", 0));

        TxtScore.setText(scores);
        TxtStatus.setText(setStatus(scores));
        audio.start();

//        tit =  findViewById(R.id.namecompany);
//        tit.setOnClickListener(this);
    }

    private String setStatus(String scores){
        int score = Integer.parseInt(scores);
        if(score <= 3){
            audio = MediaPlayer.create(this, R.raw.medium_score);
            return "Better Luck next time!";
        }
        btn.setVisibility(View.VISIBLE);
        if (score >= 5){
            audio = MediaPlayer.create(this,  R.raw.high_score);
//            return "Congratulations!You are eligible to register";    //toastt
            Toast.makeText(this, "Congratulations!You are eligible to register", Toast.LENGTH_LONG).show();
               startActivity(new Intent(this,RegisterGuard.class));

        }
        audio = MediaPlayer.create(this,  R.raw.low_score);
        return "Sorry! You are illegible to register";
    }


    public void goToHome(View v){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        finish();
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.namecompany:  //returns back
//                startActivity(new Intent(this, RegisterGuard.class));
//                break;
//        }

    }
