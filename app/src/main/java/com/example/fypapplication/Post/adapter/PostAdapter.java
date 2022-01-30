package com.example.fypapplication.Post.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.AddToFirebase;
import com.example.fypapplication.Post.model.ItemClickListener;
import com.example.fypapplication.Post.model.Post;
import com.example.fypapplication.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<AddToFirebase> postList;
    private ItemClickListener itemClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView packageName, idCompany, chargeCategory, message, packagePrice, securityType, weaponType, hours, guards, startTime, endTime, packageDays, packageDuration, overNightRate, extraHoursRate, outCityRate, description;

        public MyViewHolder(View view) {
            super(view);

            packageName = (TextView) (view.findViewById(R.id.package1));
            idCompany = (TextView) (view.findViewById(R.id.company));
            chargeCategory = (TextView) (view.findViewById(R.id.package_charge_category));
            packagePrice = (TextView) (view.findViewById(R.id.charges));
            securityType = (TextView) (view.findViewById(R.id.radio1));
            weaponType = (TextView) (view.findViewById(R.id.radio_wType));
            hours = (TextView) (view.findViewById(R.id.hours));
            guards = (TextView) (view.findViewById(R.id.radio2));
            startTime = (TextView) (view.findViewById(R.id.starttime));
            endTime = (TextView) (view.findViewById(R.id.endtime));
            packageDays = (TextView) (view.findViewById(R.id.radio_days));
            packageDuration = (TextView) (view.findViewById(R.id.radio_duration));
            overNightRate = (TextView) (view.findViewById(R.id.overnight));
            extraHoursRate = (TextView) (view.findViewById(R.id.extrahour));
            outCityRate = (TextView) (view.findViewById(R.id.out_city));
            description = (TextView) (view.findViewById(R.id.description));
            message = (TextView) (view.findViewById(R.id.message));
        }
    }

    public PostAdapter(List<AddToFirebase> postList, ItemClickListener itemClickListener) {
        this.postList = postList;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AddToFirebase post = postList.get(position);

        holder.packageName.setText(post.getPackageName());
        holder.idCompany.setText(post.getCompanyName());
        holder.packagePrice.setText(String.valueOf(post.getPackageCharges()));
        holder.securityType.setText(post.getSecurityType());
        holder.weaponType.setText(post.getWeaponType());
        holder.hours.setText(post.getDutyhours());
        holder.guards.setText(post.getNumOfGuards()
        );
        holder.startTime.setText(String.valueOf(post.getStartTime()));
        holder.endTime.setText(String.valueOf(post.getEndTime()));
        holder.packageDays.setText(post.getPackageDays());
        holder.packageDuration.setText(post.getPackageDuration());
        holder.overNightRate.setText(String.valueOf(post.getOverNightRate()));
        holder.extraHoursRate.setText(String.valueOf(post.getExtraHoursRate()));
        holder.outCityRate.setText(String.valueOf(post.getOutCityRate()));
        holder.description.setText(String.valueOf(post.getDescription()));
        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(v, position, post.getCompanyName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


}