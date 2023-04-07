package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class labourDetail extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtname, txtaadhar, txtcontact, txtcity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labour_detail);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        toolbar = findViewById(R.id.toolbar);
        txtname = findViewById(R.id.txtname);
        txtaadhar = findViewById(R.id.txtAadhar);
        txtcontact = findViewById(R.id.txtconatct);
        txtcity = findViewById(R.id.txtcity);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Labour Detail");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        String Aadhar = intent.getStringExtra("Aadhaar");
        String Name = intent.getStringExtra("Name");
        String City = intent.getStringExtra("City");
        String Contact = intent.getStringExtra("mnumber");

        txtaadhar.setText(Aadhar);
        txtname.setText(Name);
        txtcontact.setText(Contact);
        txtcity.setText(City);
    }
}