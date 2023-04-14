package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.util.ArrayList;

public class vehicleDetailsDataAdapter extends RecyclerView.Adapter<vehicleDetailsDataAdapter.viewHolder> {

    ArrayList<vehicleDetailsClass> vehicleDetailsClassArrayList;
    Context context;

    public vehicleDetailsDataAdapter(ArrayList<vehicleDetailsClass> vehicleDetailsClassArrayList, Context context) {
        this.vehicleDetailsClassArrayList = vehicleDetailsClassArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public vehicleDetailsDataAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_vehicle_type, parent, false);
        return new viewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        vehicleDetailsClass vehicleDetailsClass = vehicleDetailsClassArrayList.get(position);
        holder.vType.setText(vehicleDetailsClass.getType());
        holder.VCharge.setText(vehicleDetailsClass.getCharge());

        String imgURL = vehicleDetailsClass.getImgUrl();
        Glide.with(holder.imgVehicleType.getContext()).load(imgURL).into(holder.imgVehicleType);


    }

    @Override
    public int getItemCount() {
        return vehicleDetailsClassArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView vType , VCharge ;
        ImageView imgVehicleType;
        CardView cardVehicleType;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cardVehicleType = itemView.findViewById(R.id.cardVehicleType);
            imgVehicleType = itemView.findViewById(R.id.imgVehicleType);
            vType = itemView.findViewById(R.id.vType);
            VCharge = itemView.findViewById(R.id.vCharge);
        }
    }
}
