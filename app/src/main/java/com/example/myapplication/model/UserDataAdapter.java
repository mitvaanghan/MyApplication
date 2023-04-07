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
import com.example.myapplication.ui.userFragment;
import com.example.myapplication.userDetail;

import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.MyViewHolder> {

    private final Context context;
    private final List<Userclass> userList;

    public UserDataAdapter(Context context, List<Userclass> user) {
        this.context = context;
        this.userList = user;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Userclass userclass = userList.get(position);
        holder.name.setText(userclass.getName());
        holder.contact.setText(userclass.getContact());


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, userDetail.class);
            intent.putExtra("Name", userList.get(holder.getAdapterPosition()).getName());
            intent.putExtra("Contact",userList.get(holder.getAdapterPosition()).getContact());
            intent.putExtra("email",userList.get(holder.getAdapterPosition()).getEmail());
            intent.putExtra("city",userList.get(holder.getAdapterPosition()).getCity());
            context.startActivity(intent);
        });

    }



    @Override
    public int getItemCount() {
        return userList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, contact ;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.recCard);
            name = itemView.findViewById(R.id.recName);
            contact = itemView.findViewById(R.id.recMobile);

        }
    }
}
