package com.example.fypapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fypapplication.Guards.model.GuardAttendanceDataModel;
import com.example.fypapplication.Guards.model.GuardAttendanceModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.fypapplication.Post.utils.Constants.KEY_GUARD_ATTENDANCE;

public class CheckInCheckOutActivity extends AppCompatActivity {
    private static final String TAG = "Check Out Activity";
    Button checkin, checkout, breakstart, breakstop;
    Chronometer workingHoursCount, breakCount;
    Boolean isRunning = false, isBreakRunning = false;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String currentUUID;
    FirebaseUser user;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    GuardAttendanceDataModel attendanceDataModel;
    private DatabaseReference mdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_check_out);
        checkin = findViewById(R.id.checkin);
        checkout = findViewById(R.id.checkout);
        breakstart = findViewById(R.id.startbreak);
        breakstop = findViewById(R.id.stopbreak);
        workingHoursCount = (Chronometer) findViewById(R.id.chronometerWorking);
        breakCount = (Chronometer) findViewById(R.id.chronometerBreak);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference();
        currentUUID = user.getUid();
        attendanceDataModel = new GuardAttendanceDataModel();
        if (savedInstanceState != null || getDate() != null && getIsRunning() != null) {
            if (getDate().equals(new Date().toString()) && savedInstanceState.getLong("ChronoTime") > 0 && getIsRunning()) {
                long saveValue = savedInstanceState.getLong("ChronoTime");
                Log.d(TAG, "onCreate: save time : " + saveValue);
                workingHoursCount.setBase(saveValue);
                workingHoursCount.start();
                isRunning = true;
            }
        } else if (savedInstanceState != null || getDate() != null && getIsBreakRunning() != null) {
            if (savedInstanceState.getLong("BreakChronoTime") > 0 && getDate().equals(new Date().toString()) && getIsBreakRunning()) {
                long saveValue = savedInstanceState.getLong("BreakChronoTime") - SystemClock.elapsedRealtime();
                Log.d(TAG, "onCreate: save time : " + saveValue);
                workingHoursCount.setBase(saveValue);
                workingHoursCount.start();
                isBreakRunning = true;
            }
        }

        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. parse your input as a date object.
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date startDate = null;
                try {
                    startDate = format.parse("08:00:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //2. feed it to a Calendar Object
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);

                //3. get the hour, minute, second variable
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                Log.d(TAG, "onClick: " + workingHoursCount.getBase());
                Log.d(TAG, "onClick: is running" + isRunning);
                if (!isRunning) {
                    workingHoursCount.setBase(SystemClock.elapsedRealtime() + (hour * 60000 * 60 + minute * 60000 + second * 1000));
                    workingHoursCount.start();
                    isRunning = true;
                    attendanceDataModel.setCheck_in_time(new Date().toString());
                    attendanceDataModel.setCheck_out_time(new Date().toString());
                    attendanceDataModel.setBreak_time("30 min");
                    saveCheckInTime();
                    saveGuardAttendance(attendanceDataModel);
                }
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    workingHoursCount.stop();
                    isRunning = false;
                    attendanceDataModel.setCheck_in_time(getCheckInTime());
                    attendanceDataModel.setCheck_out_time(new Date().toString());
                    attendanceDataModel.setBreak_time("30 min");
                    saveGuardAttendance(attendanceDataModel);
                    Log.d(TAG, "onClick: " + workingHoursCount.getBase());
                }
            }
        });
        workingHoursCount.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                onChronometerTickHandler();
            }
        });

        breakstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. parse your input as a date object.
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date startDate = null;
                try {
                    startDate = format.parse("00:30:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //2. feed it to a Calendar Object
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);

                //3. get the hour, minute, second variable
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);

                Log.d(TAG, "onClick: " + breakCount.getBase());
                Log.d(TAG, "onClick: is running" + isBreakRunning);
                if (!isBreakRunning) {
                    breakCount.setBase(SystemClock.elapsedRealtime() + (hour * 60000 * 60 + minute * 60000 + second * 1000));
                    breakCount.start();
                    isBreakRunning = true;
                }
            }
        });
        breakstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBreakRunning) {
                    breakCount.stop();
                    isBreakRunning = false;
                    Log.d(TAG, "onClick: " + breakCount.getBase());
                }
            }
        });
        breakCount.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long time = breakCount.getBase() - SystemClock.elapsedRealtime();
                Log.d(TAG, "onChronometerTickHandler: " + time);
                if (time < 0 && isBreakRunning) {
                    breakCount.stop();
                    isBreakRunning = false;
                }
            }
        });
    }

    private void onChronometerTickHandler() {
        long time = workingHoursCount.getBase() - SystemClock.elapsedRealtime();
        Log.d(TAG, "onChronometerTickHandler: " + time);
        if (time < 0 && isRunning) {
            workingHoursCount.stop();
            isRunning = false;
        }
    }

    @Override
    public void onSaveInstanceState(@NotNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("ChronoTime", workingHoursCount.getBase());
        savedInstanceState.putLong("BreakChronoTime", breakCount.getBase());
        savedInstanceState.putBoolean("isChromoRunning", isRunning);
        savedInstanceState.putBoolean("isBreakChromoRunning", isBreakRunning);
    }

    private void saveCount(long currentCount) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("countTime", currentCount);
        editor.putLong("breakCountTime", currentCount);
        editor.putString("currentDate", currentDate);
        editor.putBoolean("isChromoRunning", isRunning);
        editor.putBoolean("isBreakChromoRunning", isBreakRunning);
        editor.apply();
    }
    private void saveCheckInTime() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("checkInTime", new Date().toString());
        editor.apply();
    }

    private void saveBreakCount(long currentBreakCount) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("breakCountTime", currentBreakCount);
        editor.putString("currentDate", currentDate);
        editor.putBoolean("isBreakChromoRunning", isBreakRunning);
        editor.apply();
    }

    private long getCount() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getLong("countTime", 0);
    }

    private long getBreakCount() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getLong("breakCountTime", 0);
    }

    private String getDate() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getString("currentDate", null);
    }
    private String getCheckInTime() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getString("checkInTime", null);
    }

    private Boolean getIsRunning() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getBoolean("isChromoRunning", false);
    }

    private Boolean getIsBreakRunning() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getBoolean("isBreakChromoRunning", false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveCount(workingHoursCount.getBase());
        saveBreakCount(breakCount.getBase());
    }

    @Override
    protected void onResume() {
        super.onResume();
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        if (getDate() != null && getIsRunning() != null) {
            if (getDate().equals(currentDate) && getIsRunning()) {
                Log.d(TAG, "onCreate:  get Count : " + getCount());
                workingHoursCount.setBase(getCount());
                workingHoursCount.start();
                isRunning = true;
            }
        } else if (getDate() != null && getIsBreakRunning() != null) {
            if (getDate().equals(currentDate) && getIsBreakRunning()) {
                Log.d(TAG, "onCreate:  get Count : " + getBreakCount());
                breakCount.setBase(getBreakCount());
                breakCount.start();
                isBreakRunning = true;
            }
        }
    }

    private void saveGuardAttendance(GuardAttendanceDataModel guardAttendanceDataModel) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        final GuardAttendanceModel dataModel = new GuardAttendanceModel(currentUUID, currentDate, guardAttendanceDataModel);
        FirebaseDatabase.getInstance().getReference(KEY_GUARD_ATTENDANCE)
                .orderByChild("duty_date")
                .equalTo(dataModel.getDuty_date())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            FirebaseDatabase.getInstance().getReference(KEY_GUARD_ATTENDANCE).child(currentUUID).setValue(dataModel);
                        } else {
                            FirebaseDatabase.getInstance().getReference(KEY_GUARD_ATTENDANCE).child(currentUUID).push().setValue(dataModel);
                        }
                        // do what you want
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

//        FirebaseDatabase.getInstance().getReference(KEY_GUARD_ATTENDANCE).child(currentUUID).push().setValue(dataModel)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(), "Guard Attendance has been added successfully", Toast.LENGTH_LONG).show();
//                        } else {
//                    Toast.makeText(getApplicationContext(), "Guard Attendance Failed" + task.getException(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });

    }

}