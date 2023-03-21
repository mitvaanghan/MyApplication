package com.example.myapplication.ui;

import static com.example.myapplication.MainActivity.toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profileFragment extends Fragment {

    TextInputEditText edtname , edtemail , edtcontact , edtcity;
    FirebaseAuth mAuth;
    Button btnupdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);


        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null){
            actionBar.setTitle("Profile");
        }

        edtname = view.findViewById(R.id.edtUname);
        edtemail = view.findViewById(R.id.edtEmail);
        edtcontact = view.findViewById(R.id.edtCnumber);
        edtcity = view.findViewById(R.id.edtCity);
        btnupdate = view.findViewById(R.id.btnupdate);

        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        edtemail.setText(email);
        String st = email.replaceAll("\\.",",");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Admin/Registration");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String name = snapshot.child(st).child("name").getValue(String.class);
                        String email = snapshot.child( st).child("email").getValue(String.class);
                        String contact = snapshot.child(st).child("contact").getValue(String.class);
                        String city = snapshot.child(st).child("city").getValue(String.class);

                        edtname.setText(name);
                        edtemail.setText(email);
                        edtcontact.setText(contact);
                        edtcity.setText(city);

                        btnupdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (!name.isEmpty()){
                                    edtname.setError(null);
                                    if (!email.isEmpty()){
                                        edtemail.setError(null);
                                        if (!contact.isEmpty()) {
                                            edtcontact.setError(null);
                                            if (!city.isEmpty()){
                                                edtcity.setError(null);
                                                    if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                                                        edtemail.setError(null);
                                                        if (contact.length()==10){
                                                            edtcontact.setError(null);

                                                                databaseReference.child(st).child("name").setValue(edtname.getText().toString().trim());
                                                                databaseReference.child(st).child("email").setValue(edtemail.getText().toString().trim());
                                                                databaseReference.child(st).child("contact").setValue(edtcontact.getText().toString().trim());
                                                                databaseReference.child(st).child("city").setValue(edtcity.getText().toString().trim());

                                                                Toast.makeText(getActivity(), "Profile is Changed Successfully", Toast.LENGTH_SHORT).show();
                                                        }
                                                            else {
                                                                edtcontact.setError("Contact Number must be in proper formate");
                                                            }
                                                }else {
                                                        edtemail.setError("Email must be in proper Formate");
                                                    }
                                            }else {
                                                edtcity.setError("Enter City");
                                            }
                                        }else {
                                            edtcontact.setError("Enter Contact Number");
                                        }
                                    }else {
                                        edtemail.setError("Enter Email Id");
                                    }
                                }else {
                                    edtname.setError("Enter Name");
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(getActivity(), "Data is not exists...Please check again", Toast.LENGTH_SHORT).show();
                    }
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