package com.example.fypapplication.Company;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fypapplication.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    EditText input, input2,input3,input4;
    Button submit;
    ListView show;
    public static ArrayList<String> data=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        input=(EditText)findViewById(R.id.edittext1);
        input2=findViewById(R.id.edittext2);
        input3=findViewById(R.id.edittext3);
        input4=findViewById(R.id.edittext4);
        submit=(Button)findViewById(R.id.submit_button);
        show=(ListView)findViewById(R.id.list1);

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (input.getText().toString()==null || input.getText().toString().trim().equals("")){
                   Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
               }
               else if (input2.getText().toString()==null || input2.getText().toString().trim().equals("")){
                   Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
               }
               else if (input3.getText().toString()==null || input3.getText().toString().trim().equals("")){
                   Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
               }
               else if (input4.getText().toString()==null || input4.getText().toString().trim().equals("")){
                   Toast.makeText(getBaseContext(),"Input Field is Empty", Toast.LENGTH_LONG).show();
               }
               else if (data.contains(input.getText().toString())){
                   Toast.makeText(getBaseContext(),"You've already entered that..", Toast.LENGTH_LONG).show();
               }
               else if (data.contains(input2.getText().toString())){
                   Toast.makeText(getBaseContext(),"You've already entered that..", Toast.LENGTH_LONG).show();
               }
               else if (data.contains(input3.getText().toString())){
                   Toast.makeText(getBaseContext(),"You've already entered that..", Toast.LENGTH_LONG).show();
               }
               else if (data.contains(input4.getText().toString())){
                   Toast.makeText(getBaseContext(),"You've already entered that..", Toast.LENGTH_LONG).show();
               }
               else {
                   data.add(input.getText().toString());
                   data.add(input2.getText().toString());
                   data.add(input3.getText().toString());
                   data.add(input4.getText().toString());
                   ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1, data);
                   show.setAdapter(adapter);
                   input.setText("");
                   input2.setText("");
                   input3.setText("");
                   input4.setText("");
               }
           }
       });
    }
}