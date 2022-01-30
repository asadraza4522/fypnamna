package com.example.fypapplication.Company;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fypapplication.R;

public class GuardRequestAdapter extends RecyclerView.Adapter<GuardRequestAdapter.ViewHolder> {
   public GuardRequestModelClass[] modelclass;

    public GuardRequestAdapter(GuardRequestModelClass[] modelclass) {
        this.modelclass = modelclass;
    }

    @NonNull
    @Override
    public GuardRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View modellist=layoutInflater.inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder= new ViewHolder(modellist);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GuardRequestAdapter.ViewHolder holder, int position) {
     final GuardRequestModelClass guardRequestModelClass = modelclass[position];
        holder.textView.setText(modelclass[position].getName());
        holder.textView1.setText(modelclass[position].getAvailableno());
        holder.textView2.setText(modelclass[position].getNumber());
    }

    @Override
    public int getItemCount() {
        return modelclass.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textView1;
        public TextView textView2;


        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.companyname);
            this.textView1=itemView.findViewById(R.id.availableguards);
            this.textView2=itemView.findViewById(R.id.contact);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(v.getContext(),
                            GuardRequirment.class);
                    v.getContext().startActivity(intent);
                }
            });

    }
}
}
