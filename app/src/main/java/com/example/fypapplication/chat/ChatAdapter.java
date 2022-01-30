package com.example.fypapplication.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fypapplication.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private ArrayList<MessageModel> messageList;
    private String currentId;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textOtherUser, textCurrentUser;
        public RelativeLayout rightView, leftView;

        public MyViewHolder(View view) {
            super(view);
            textCurrentUser = (TextView) (view.findViewById(R.id.textCurrentUser));
            textOtherUser = (TextView) (view.findViewById(R.id.textOtherUser));
            rightView = (RelativeLayout) (view.findViewById(R.id.rightView));
            leftView = (RelativeLayout) (view.findViewById(R.id.leftView));
        }
    }

    public ChatAdapter(ArrayList<MessageModel> messageList, String currentId) {
        this.messageList = messageList;
        this.currentId = currentId;
    }

    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new ChatAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.MyViewHolder holder, final int position) {
        final MessageModel model = messageList.get(position);
        holder.textCurrentUser.setText(model.getText());
        holder.textOtherUser.setText(model.getText());

        if (currentId.equals(model.getUid())) {
            holder.rightView.setVisibility(View.VISIBLE);
        } else
            holder.leftView.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

}
