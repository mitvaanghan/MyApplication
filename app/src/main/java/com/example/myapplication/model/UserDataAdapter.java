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
        holder.email.setText(userclass.getEmail());
        holder.contact.setText(userclass.getContact());
        holder.city.setText(userclass.getCity());


        holder.card.setOnClickListener(v -> {
            Intent intent = new Intent(context, userFragment.class);
            intent.putExtra("Name", userList.get(holder.getAdapterPosition()).getName());
            intent.putExtra("Email", userList.get(holder.getAdapterPosition()).getEmail());
            intent.putExtra("Contact",userList.get(holder.getAdapterPosition()).getContact());
            intent.putExtra("City",userList.get(holder.getAdapterPosition()).getCity());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email , contact , city;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.recCard);
            name = itemView.findViewById(R.id.recName);
            email = itemView.findViewById(R.id.recemail);
            contact = itemView.findViewById(R.id.reccontact);
            city = itemView.findViewById(R.id.reccity);

        }
    }
}
