package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class homeFragment extends Fragment {

        ImageSlider imageSlider;
        CardView cardtotalrevenue , cardtotalvehicleowner , cardtotalride , cardtotaluser;
        TextView txttotalrevenue , txttotalvehicleowner , txttotaluser , txttotalrides;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference datauser , datavehicleowner;
        int conutuser =0;
        int conutvehicleowner =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cardtotalrevenue = view.findViewById(R.id.cardtRevenue);
        cardtotalvehicleowner = view.findViewById(R.id.cardtVehicleOwner);
        cardtotalride = view.findViewById(R.id.cardtRide);
        cardtotaluser = view.findViewById(R.id.cardtUser);

        imageSlider =view.findViewById(R.id.imgslider);

        ArrayList<SlideModel> imagelist = new ArrayList<>();
        imagelist.add(new SlideModel(R.drawable.imgslider1, ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.imgslider2,ScaleTypes.FIT));

        imageSlider.setImageList(imagelist,ScaleTypes.FIT);

        txttotalvehicleowner = view.findViewById(R.id.txttotalVOwner);
        firebaseDatabase = FirebaseDatabase.getInstance();
        datavehicleowner = firebaseDatabase.getReference("VehicalOwner/Registration");
        datavehicleowner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    conutvehicleowner = (int) snapshot.getChildrenCount();
                    txttotalvehicleowner.setText(String.valueOf(conutvehicleowner));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txttotaluser = view.findViewById(R.id.txttotalUSer);
        firebaseDatabase = FirebaseDatabase.getInstance();
        datauser = firebaseDatabase.getReference("User/Registration");
        datauser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    conutuser = (int) snapshot.getChildrenCount();
                    txttotaluser.setText(String.valueOf(conutuser));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        cardtotalvehicleowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardtotalrevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardtotalride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cardtotaluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}