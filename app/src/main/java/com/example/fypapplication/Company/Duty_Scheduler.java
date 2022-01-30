package com.example.fypapplication.Company;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.AddToFirebase;
import com.example.fypapplication.Guards.Guard_AddToFirebase;
import com.example.fypapplication.R;
import com.example.fypapplication.RegisterGuard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Duty_Scheduler extends AppCompatActivity {
    ArrayAdapter arrayAdapter;
    EditText dutytime,dutylocation;
    Spinner guards_spinner;
    Button submit;
    String guardId;
    List<String> guardslist;
    FirebaseAuth auth;

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
        setContentView(R.layout.activity_duty_scheduler);
        submit=findViewById(R.id.submit);
guards_spinner=findViewById(R.id.guards_list);
        dutylocation=findViewById(R.id.duty_location);
        dutytime=findViewById(R.id.duty_time);

        guardslist=new ArrayList<>();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

scheleduty(dutylocation.getText().toString(),dutytime.getText().toString(),guardId);

            }
        });

       auth=FirebaseAuth.getInstance();

        fetchData("Guards");
         arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,guardslist);

       guards_spinner.setAdapter(arrayAdapter);

   guards_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

           guardId=guardslist.get(position);
       }

       @Override
       public void onNothingSelected(AdapterView<?> parent) {

       }
   });
    }

    private void fetchData(String type) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(type);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                for (DataSnapshot single : dataSnapshot.getChildren()) {

                  //  AddToFirebase post = single.getValue(AddToFirebase.class);

                    FetchGuardList fetchGuardList=single.getValue(FetchGuardList.class);

                    assert fetchGuardList != null;

                    guardslist.add(fetchGuardList.getName());

                    arrayAdapter.notifyDataSetChanged();

               //     Log.e("TAG", "Data received:" + single.g());

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "fetchData onCancelled", databaseError.toException());

            }
        };
        myRef.addListenerForSingleValueEvent(postListener);


    }


    void scheleduty(String guardname,String time,String guardId2) {

        showProgress("Adding schele","please wait");
Add_Duty_Schedule duty_schedule=new Add_Duty_Schedule(guardname,time);

FirebaseDatabase.getInstance().getReference("Guards_duty").child(guardId2).setValue(duty_schedule).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Duty_Scheduler.this, "Duty scheduled successfully", Toast.LENGTH_SHORT).show();

                    dutylocation.setText("");
                    dutytime.setText("");

                    progressDialog.cancel();
                } else {

                    Toast.makeText(Duty_Scheduler.this, " Failed to add" + task.getException(), Toast.LENGTH_SHORT).show();

                    progressDialog.cancel();
                }
            }
        });


    }
}