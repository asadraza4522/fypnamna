package com.example.fypapplication.inbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fypapplication.Post.model.ItemClickListener;
import com.example.fypapplication.R;

import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.MyViewHolder> {

    private List<InboxModel> inboxList;
    private ItemClickListener itemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView email, lastMessage;
        public ConstraintLayout container;

        public MyViewHolder(View view) {
            super(view);
            email = (TextView) (view.findViewById(R.id.email));
            lastMessage = (TextView) (view.findViewById(R.id.lastMessage));
            container = (ConstraintLayout) (view.findViewById(R.id.container));
        }
    }

    public InboxAdapter(List<InboxModel> inboxList, ItemClickListener itemClickListener) {
        this.inboxList = inboxList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public InboxAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inbox, parent, false);
        return new InboxAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InboxAdapter.MyViewHolder holder, final int position) {
        final InboxModel model = inboxList.get(position);
        holder.email.setText(model.getEmail());
        holder.lastMessage.setText(model.getLastMessage());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(v, position, model.getUserId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return inboxList.size();
    }

}
