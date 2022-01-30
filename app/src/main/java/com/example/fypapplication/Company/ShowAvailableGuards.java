package com.example.fypapplication.Company;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fypapplication.R;

public class ShowAvailableGuards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_available_guards);
        GuardRequestModelClass[] guardRequestModelClasses=new GuardRequestModelClass[]{
                new GuardRequestModelClass("AWS Security", "Available Guards ::  5","Contact No  ::  03482465682"),
                new GuardRequestModelClass("SSG Security", "Available Guards ::  3","Contact No  ::  03542554545"),
                new GuardRequestModelClass("Cling Security", "Available Guards ::  6","Contact No  ::  0355451555"),
                new GuardRequestModelClass("PLS Security", "Available Guards ::  10","Contact No  ::  0354515454")

        };
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        GuardRequestAdapter adapter= new GuardRequestAdapter(guardRequestModelClasses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}