package com.example.fypapplication.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.fypapplication.Post.utils.Constants;
import com.example.fypapplication.R;
import com.example.fypapplication.inbox.InboxModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.fypapplication.Post.utils.Constants.KEY_INBOX;
import static com.example.fypapplication.Post.utils.Constants.KEY_MESSAGE;
import static com.example.fypapplication.Post.utils.Constants.KEY_OTHER_USER_ID;

public class ChattingActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editWriteMessage;
    private ImageButton btnSend;
    private ArrayList<MessageModel> messageList;
    private RecyclerView recyclerView;
    private ChatAdapter mAdapter;
    private FirebaseAuth mAuth;
    private String otherUserId, currentUserId;
    //    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        loadDataFromBundle();
        initView();
        setListener();
    }

    @Override
    public void onClick(View v) {
        validateAndSendMessage();
    }

    private void initView() {
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getUid();
        editWriteMessage = findViewById(R.id.editWriteMessage);
        btnSend = (ImageButton) (findViewById(R.id.btnSend));
        recyclerView = findViewById(R.id.recyclerChat);
        messageList = new ArrayList<>();
        setRecyclerview();
        fetchData();
//        progressBar = (ProgressBar) (findViewById(R.id.progressBar));
    }

    private void fetchData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Constants.KEY_MESSAGE + "/" + currentUserId + "_" + otherUserId);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                messageList.clear();
                for (DataSnapshot single : dataSnapshot.getChildren()) {
                    MessageModel msg = single.getValue(MessageModel.class);
                    messageList.add(msg);
                    Log.e("TAG", "Data received:" + msg.getText());
                }
                mAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(messageList.size() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "fetchData onCancelled", databaseError.toException());
            }
        };
        myRef.addListenerForSingleValueEvent(postListener);
    }

    private void setListener() {
        btnSend.setOnClickListener(this);
    }

    private void loadDataFromBundle() {
        otherUserId = getIntent().getStringExtra(KEY_OTHER_USER_ID);
    }

    private void validateAndSendMessage() {
        final String messageText = editWriteMessage.getText().toString().trim();
        if (messageText.isEmpty()) {
            editWriteMessage.setError("Again enter your message!");
            editWriteMessage.requestFocus();
            return;
        } else {
            sendMessageToFirebase(messageText);
        }
    }

    private void sendMessageToFirebase(String messageText) {

        MessageModel senderMsg = new MessageModel(
                currentUserId,
                messageText
        );

        InboxModel inbox = new InboxModel(
                currentUserId,
                mAuth.getCurrentUser().getEmail(),
                editWriteMessage.getText().toString()
        );

        FirebaseDatabase.getInstance().getReference(KEY_MESSAGE).child(currentUserId + "_" + otherUserId).push().setValue(senderMsg)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
//                    Toast.makeText(ChattingActivity.this, "Message has been added successfully", Toast.LENGTH_LONG).show();
                        } else {
//                    Toast.makeText(ChattingActivity.this, "Message Failed" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
        FirebaseDatabase.getInstance().getReference(KEY_MESSAGE).child(otherUserId + "_" + currentUserId).push().setValue(senderMsg).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    editWriteMessage.getText().clear();
                    mAdapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messageList.size() - 1);
//                    Toast.makeText(ChattingActivity.this, "Message has been added successfully", Toast.LENGTH_LONG).show();
                } else {
//                    Toast.makeText(ChattingActivity.this, "Message Failed" + task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });


        FirebaseDatabase.getInstance().getReference(KEY_INBOX)
                .child(otherUserId)
                .setValue(inbox)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //    Toast.makeText(ChattingActivity.this, "Inbox Message has been added successfully.", Toast.LENGTH_LONG).show();
                        } else {
                            //  Toast.makeText(ChattingActivity.this, "Inbox Message Failed" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void setRecyclerview() {
        mAdapter = new ChatAdapter(messageList, currentUserId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}