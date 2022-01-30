package com.example.fypapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    private ImageView tit;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<Question> questions;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hide the keyboard
        setContentView(R.layout.activity_quiz);

        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);
        tit =  findViewById(R.id.namecompany);
        tit.setOnClickListener(this);


        questions = new ArrayList<Question>(){
            {
                add(new Question("Criminal maturity a guard should?", "A", "18 years ", "14 years ","12 years ", "8 years "));
                add(new Question("Partiality can be affected by the following?", "D", "Attitude", "Prejudice","Discrimination", "None of the above"));
                add(new Question("How many time police can enter into private property?", "D", "One", "Nine","Eight", "Two"));
                add(new Question("Division of statute law is two.", "B", "federal law and labour law", "Criminal and civil", "Criminal", "Penal code and federal"));
                add(new Question("Information security includes?", "A", "Radio", "Remote", "Ups", "Stick"));
//                add(new Question("Em tecnologia, o que é I.A?", "D", "Software", "Sistema Operacional", "Compilador", "Interligência Artificial"));
//                add(new Question("Quanto vale 8 bits?", "C", "1 Bit", "16 Bytes", "1 Byte", "1 Mega Byte"));
//                add(new Question("O que é Bitcoin?", "B", "Moeda governamental", "Crypto Moeda", "Uma rede decentralizada", "Software de Datamining"));
//                add(new Question("Quem foi que criou o Bitcoin?", "B", "Margaret Hamilton", "Satoshi Nakamoto", "Alan Turing", "Gustavo Guanabara"));
//                add(new Question("Quem foi o primeiro programador?", "D", "Steve Jobs", "Linus Torvalds", "Alan Turing", "Ada Lovelace"));
            }

        };

        loadQuestion();
    }



    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();



    }


    private void loadQuestion(){
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.opcaoA:
                Answer="A";
                break;

            case R.id.opcaoB:
                Answer="B";
                break;

            case R.id.opcaoC:
                Answer="C";
                break;

            case R.id.opcaoD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.namecompany:  //returns back
                startActivity(new Intent(this, RegisterGuard.class));
                break;
        }
    }
}





