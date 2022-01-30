package com.example.fypapplication.Company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fypapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CompanyAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_account);
        BottomNavigationView bottomNav = findViewById(R.id.bottom3);
        bottomNav.setSelectedItemId(R.id.navbar);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home1:
                        startActivity(new Intent(getApplicationContext(), CompanyDashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.exchange:
                        startActivity(new Intent(getApplicationContext(), ExploreActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.navbar:
                        return true;
                }
                return false;
            }
        });
    }
}