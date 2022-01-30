package com.example.fypapplication.Post.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.AddToFirebase;
import com.example.fypapplication.Post.adapter.PostAdapter;
import com.example.fypapplication.Post.model.ItemClickListener;
import com.example.fypapplication.Post.utils.Constants;
import com.example.fypapplication.R;
import com.example.fypapplication.chat.ChattingActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.fypapplication.Post.utils.Constants.KEY_OTHER_USER_ID;

public class PostListActivity extends AppCompatActivity {
    private List<AddToFirebase> postList = new ArrayList<AddToFirebase>();
    private RecyclerView recyclerView;
    private PostAdapter mAdapter;
    private ProgressBar progressBar;
    private TextView noDataFound;
    String[] type = { "Standard","Premium","on sale"};
    Spinner s3;
    private ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onClick(View view, int position, String otherId) {
            Intent i = new Intent(PostListActivity.this, ChattingActivity.class);
            i.putExtra(KEY_OTHER_USER_ID, otherId);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        noDataFound = (TextView) (findViewById(R.id.noDataFound));
        progressBar = (ProgressBar) (findViewById(R.id.progressBar));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        s3=findViewById(R.id.spin3);
        ArrayAdapter ap = new ArrayAdapter(this,android.R.layout.simple_spinner_item,type);
        s3.setAdapter(ap);
        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(type[position].toLowerCase().contains("premium")){
                    fetchData(Constants.premium);
                } else  if(type[position].toLowerCase().contains("standard")){
                    fetchData(Constants.standard);
                }
                else  if(type[position].toLowerCase().contains("on sale")){
                    fetchData(Constants.onsale);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fetchData(Constants.standard);
    }

    private void setRecyclerview(List<AddToFirebase> list) {
        mAdapter = new PostAdapter(list, itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void fetchData(String type) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(type);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                postList.clear();
                for (DataSnapshot single : dataSnapshot.getChildren()) {
                    AddToFirebase post = single.getValue(AddToFirebase.class);
                    postList.add(post);
                    Log.e("TAG", "Data received:" + post.toString());

                }
                setRecyclerview(postList);
                noDataFound.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "fetchData onCancelled", databaseError.toException());
                noDataFound.setVisibility(View.VISIBLE);
            }
        };
        myRef.addListenerForSingleValueEvent(postListener);
        progressBar.setVisibility(View.GONE);
    }
}