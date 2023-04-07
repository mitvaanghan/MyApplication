package com.example.myapplication.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.vehicleOwnerFragment;
import com.example.myapplication.vehicleOwnerDetail;

import java.util.List;

public class vehicleOwnerDataAdapter extends RecyclerView.Adapter<vehicleOwnerDataAdapter.MyViewHolder> {

    private final Context context;
    private final List<vehicleOwnerClass> vehicleOwnerClassList;

    public vehicleOwnerDataAdapter(Context context, List<vehicleOwnerClass> vehicle ) {
        this.context = context;
        this.vehicleOwnerClassList = vehicle;
    }

    @NonNull
    @Override
    public vehicleOwnerDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_vehicle_owner, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        vehicleOwnerClass vehicleOwnerClass = vehicleOwnerClassList.get(position);
        holder.name.setText(vehicleOwnerClass.getName());
        holder.contact.setText(vehicleOwnerClass.getContact());

        holder.recCard.setOnClickListener(v -> {
            Intent intent = new Intent(context , vehicleOwnerDetail.class);
            intent.putExtra("Name",vehicleOwnerClassList.get(holder.getAdapterPosition()).getName());
            intent.putExtra("Contact",vehicleOwnerClassList.get(holder.getAdapterPosition()).getContact());
            intent.putExtra("email",vehicleOwnerClassList.get(holder.getAdapterPosition()).getEmail());
            intent.putExtra("city",vehicleOwnerClassList.get(holder.getAdapterPosition()).getCity());

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return vehicleOwnerClassList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name , contact;
        CardView recCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recCard = itemView.findViewById(R.id.recCard);
            name = itemView.findViewById(R.id.recName);
            contact = itemView.findViewById(R.id.recMobile);
        }
    }
}
