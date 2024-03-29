package com.example.myapplication.ui;

import static com.example.myapplication.MainActivity.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.model.LabourClass;
import com.example.myapplication.model.LabourDataAdapter;
import com.example.myapplication.model.vehicleOwnerClass;
import com.example.myapplication.model.vehicleOwnerDataAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class vehicleOwnerFragment extends Fragment {

    RecyclerView recyclerView;
    List<vehicleOwnerClass> vehicleOwnerClassList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vehicle_owner, container, false);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("All VehicleOwner Details");
        }

        recyclerView = view.findViewById(R.id.recyclerviewVehicleOwner);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("VehicleOwner/Registration");
        vehicleOwnerClassList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        vehicleOwnerDataAdapter adapter = new vehicleOwnerDataAdapter(getContext(),vehicleOwnerClassList);
        recyclerView.setAdapter(adapter);

        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vehicleOwnerClassList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {
                    vehicleOwnerClass vehicleOwnerClass = itemSnapshot.getValue(vehicleOwnerClass.class);
                    vehicleOwnerClassList.add(vehicleOwnerClass);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()==1&& keyCode==4){
                    replaceFragment(new homeFragment());
                }
                return false;
            }
        });
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction beginTransction =getActivity().getSupportFragmentManager().beginTransaction();
        beginTransction.replace(R.id.fragment,fragment);
        beginTransction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        beginTransction.commit();
        toolbar.setTitle("Home");
    }
}