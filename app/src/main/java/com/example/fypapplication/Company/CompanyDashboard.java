package com.example.fypapplication.Company;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.fypapplication.Post.activity.NewPostActivity;
import com.example.fypapplication.Post.activity.PostListActivity;
import com.example.fypapplication.Profit_generator;
import com.example.fypapplication.R;
import com.example.fypapplication.inbox.InboxActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CompanyDashboard extends AppCompatActivity {
    ImageView notifications1,notification2;
    CardView card1, card2, card3, card4,card5,card6,card7,card8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_dashboard);
        notifications1 = findViewById(R.id.notifications);
        notification2=findViewById(R.id.notification2);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notifications", "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        notification2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(CompanyDashboard.this, "My Notifications")
                        .setContentTitle("Request Denied")
                        .setContentText("Your request for guard sefvices is cancelled by AWS Security")
                        .setSmallIcon(R.drawable.service)
                        .setAutoCancel(true);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(CompanyDashboard.this);
                managerCompat.notify(999, builder.build());
            }
        });
        notifications1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(CompanyDashboard.this, "My Notifications")
                        .setContentTitle("Request Approved")
                        .setContentText("Your request for guard sefvices is approved by AWS Security")
                        .setSmallIcon(R.drawable.service)
                        .setAutoCancel(true);
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(CompanyDashboard.this);
                managerCompat.notify(991, builder.build());
            }
        });
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6=findViewById(R.id.card6);
        card7=findViewById(R.id.card7);
        card8=findViewById(R.id.card8);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(CompanyDashboard.this, Duty_Scheduler.class);
                startActivity(i);

            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CompanyDashboard.this, ScheduleActivity.class);
                startActivity(i);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CompanyDashboard.this, NewPostActivity.class);
                startActivity(i);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CompanyDashboard.this, PostListActivity.class);
                startActivity(i);
            }
        });
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CompanyDashboard.this, Profit_generator.class);
                startActivity(i);
            }
        });
        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CompanyDashboard.this, ShowAvailableGuards.class);
                startActivity(i);
            }
        });
        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CompanyDashboard.this, ExchangeRequest.class);
                startActivity(i);
            }
        });
        card8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CompanyDashboard.this, ListActivity.class);
                startActivity(i);
            }
        });
        BottomNavigationView bottomNav = findViewById(R.id.bottom1);
        bottomNav.setSelectedItemId(R.id.nav_home1);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home1:
                        return true;
                    case R.id.exchange:
                        startActivity(new Intent(getApplicationContext(), ExploreActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_inbox:
                        startActivity(new Intent(getApplicationContext(), InboxActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navbar:
                        startActivity(new Intent(getApplicationContext(), CompanyAccountActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}
