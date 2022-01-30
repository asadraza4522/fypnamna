package com.example.fypapplication.Company;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.fypapplication.R;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {
    Button addButton;
    ListView itemListView;
    public static final int CREATE_REQUEST = 1;
    public static ArrayList<String> arrayList;
    public static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Bundle editTextData = getIntent().getExtras();

        if (editTextData != null) {

            itemListView = (ListView) findViewById(R.id.itemListView);
            String data = editTextData.getString("data");
            arrayList = new ArrayList<String>();
            arrayList.add(data);
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            itemListView.setAdapter(arrayAdapter);
            if (data != "") {

                arrayAdapter.notifyDataSetChanged();
            }
        }
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScheduleActivity.this, CreateScheduleActivity.class);
                startActivityForResult(intent, CREATE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_REQUEST) {
            if (resultCode == RESULT_OK) {
                String click = data.getStringExtra("data");
                arrayList.add(click);
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
                itemListView.setAdapter(arrayAdapter);
            }
        }
    }
}