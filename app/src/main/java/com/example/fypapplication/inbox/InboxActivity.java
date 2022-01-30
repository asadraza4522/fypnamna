package com.example.fypapplication.inbox;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fypapplication.Post.model.ItemClickListener;
import com.example.fypapplication.Post.utils.Constants;
import com.example.fypapplication.R;
import com.example.fypapplication.chat.ChattingActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.fypapplication.Post.utils.Constants.KEY_OTHER_USER_ID;

public class InboxActivity extends AppCompatActivity {

    private List<InboxModel> inboxList = new ArrayList<>();
    private RecyclerView recyclerView;
    private InboxAdapter mAdapter;
    private FirebaseAuth mAuth;

    private ItemClickListener itemClickListener = new ItemClickListener() {
        @Override
        public void onClick(View view, int position, String otherId) {
            Intent i = new Intent(InboxActivity.this, ChattingActivity.class);
            i.putExtra(KEY_OTHER_USER_ID, otherId);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAuth = FirebaseAuth.getInstance();
        fetchData();
    }

    private void setRecyclerview(List<InboxModel> list) {
        mAdapter = new InboxAdapter(list, itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void fetchData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constants.KEY_INBOX);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Inbox object and use the values to update the UI
                inboxList.clear();
                for (DataSnapshot single : dataSnapshot.getChildren()) {
                    InboxModel inbox = single.getValue(InboxModel.class);
                    inboxList.add(inbox);
                    Log.e("TAG", "Data Inbox:" + Objects.requireNonNull(inbox).toString());
                }
                setRecyclerview(inboxList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "fetchData onCancelled", databaseError.toException());
            }
        };
        myRef.addListenerForSingleValueEvent(postListener);
    }
}