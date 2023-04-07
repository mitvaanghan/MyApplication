package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminProfile extends AppCompatActivity {

    TextInputEditText edtname , edtemail , edtcontact , edtcity;
    FirebaseAuth mAuth;
    Button btnupdate;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        edtname = findViewById(R.id.edtUname);
        edtemail = findViewById(R.id.edtEmail);
        edtcontact = findViewById(R.id.edtCnumber);
        edtcity = findViewById(R.id.edtCity);
        btnupdate = findViewById(R.id.btnupdate);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Edit Profile");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
//                            if (!name.isEmpty()){
//                                edtname.setError(null);
//                                if (!email.isEmpty()){
//                                    edtemail.setError(null);
//                                    if (!contact.isEmpty()) {
//                                        edtcontact.setError(null);
//                                        if (!city.isEmpty()){
//                                            edtcity.setError(null);
//                                            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                                                edtemail.setError(null);
//                                                if (contact.length()==10){
//                                                    edtcontact.setError(null);

                                                    databaseReference.child(st).child("name").setValue(edtname.getText().toString().trim());
                                                    databaseReference.child(st).child("email").setValue(edtemail.getText().toString().trim());
                                                    databaseReference.child(st).child("contact").setValue(edtcontact.getText().toString().trim());
                                                    databaseReference.child(st).child("city").setValue(edtcity.getText().toString().trim());

                                                    Toast.makeText(adminProfile.this, "Profile is Changed Successfully", Toast.LENGTH_SHORT).show();
//                                                }
//                                                else {
//                                                    edtcontact.setError("Contact Number must be in proper formate");
//                                                }
//                                            }else {
//                                                edtemail.setError("Email must be in proper Formate");
//                                            }
//                                        }else {
//                                            edtcity.setError("Enter City");
//                                        }
//                                    }else {
//                                        edtcontact.setError("Enter Contact Number");
//                                    }
//                                }else {
//                                    edtemail.setError("Enter Email Id");
//                                }
//                            }else {
//                                edtname.setError("Enter Name");
//                            }
                        }
                    });
                }
                else {
                    Toast.makeText(adminProfile.this, "Data is not exists...Please check again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}