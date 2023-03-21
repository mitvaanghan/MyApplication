package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class adminProfile extends AppCompatActivity {

    TextInputEditText edtname , edtemail , edtcontact , edtcity;
    FirebaseAuth mAuth;
    Button btnupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        edtname = findViewById(R.id.edtUname);
        edtemail = findViewById(R.id.edtEmail);
        edtcontact = findViewById(R.id.edtCnumber);
        edtcity = findViewById(R.id.edtCity);
        btnupdate = findViewById(R.id.btnupdate);
    }
}