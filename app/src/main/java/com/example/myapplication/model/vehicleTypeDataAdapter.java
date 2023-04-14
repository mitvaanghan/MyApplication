package com.example.myapplication.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class vehicleTypeDataAdapter extends RecyclerView.Adapter<vehicleTypeDataAdapter.MyViewHolder> {

    private final Context context;
    private final List<vehicleTypeClass> vehicleTypeClassList;

    public vehicleTypeDataAdapter(Context context, List<vehicleTypeClass> vehicleTypeClassList) {
        this.context = context;
        this.vehicleTypeClassList = vehicleTypeClassList;
    }

    @NonNull
    @Override
    public vehicleTypeDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_vehicle_owner_detail, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        vehicleTypeClass vehicleTypeClass = vehicleTypeClassList.get(position);
        holder.vType.setText(vehicleTypeClass.getType());
        holder.vplateno.setText(vehicleTypeClass.getPnumber());
        holder.dName.setText(vehicleTypeClass.getDrivername());
        holder.dContactno.setText(vehicleTypeClass.getDrivercnumber());

    }

    @Override
    public int getItemCount() {
        return vehicleTypeClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView vType , vplateno , dName , dContactno;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vType = itemView.findViewById(R.id.txtType);
            vplateno = itemView.findViewById(R.id.txtPlateno);
            dName = itemView.findViewById(R.id.txtdName);
            dContactno = itemView.findViewById(R.id.txtdContactno);

        }
    }
}
