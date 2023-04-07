package com.example.myapplication.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.labourDetail;
import com.example.myapplication.ui.labourManagFragment;

import java.util.List;

public class LabourDataAdapter extends RecyclerView.Adapter<LabourDataAdapter.MyViewHolder>{

    private final Context context;
    private final List<LabourClass> labourClassList;

    public LabourDataAdapter(Context context, List<LabourClass> labour ) {
        this.context = context;
        this.labourClassList = labour;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_labour, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        LabourClass labourClass = labourClassList.get(position);
        holder.name.setText(labourClass.getName());
        holder.mnumber.setText(labourClass.getMnumber());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context , labourDetail.class);
            intent.putExtra("Name",labourClassList.get(holder.getAdapterPosition()).getName());
            intent.putExtra("mnumber",labourClassList.get(holder.getAdapterPosition()).getMnumber());
            intent.putExtra("Aadhaar",labourClassList.get(holder.getAdapterPosition()).getAadhar());
            intent.putExtra("City",labourClassList.get(holder.getAdapterPosition()).getAddress());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return labourClassList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,mnumber;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.recCard);
            name = itemView.findViewById(R.id.recNameLabour);
            mnumber = itemView.findViewById(R.id.recMobileLabour);

        }
    }


}
