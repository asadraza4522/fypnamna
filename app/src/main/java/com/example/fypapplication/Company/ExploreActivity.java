package com.example.fypapplication.Company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fypapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExploreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        BottomNavigationView bottomNav = findViewById(R.id.bottom2);
        bottomNav.setSelectedItemId(R.id.exchange);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home1:
                        startActivity(new Intent(getApplicationContext(), CompanyDashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.exchange:
                        return true;
                    case R.id.navbar:
                        startActivity(new Intent(getApplicationContext(), CompanyAccountActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}