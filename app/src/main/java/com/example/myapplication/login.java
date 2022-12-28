package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button  signin = findViewById(R.id.signin);
        TextView signup = findViewById(R.id.signup);
        TextView forgotpassword = findViewById(R.id.forgotpassword);

    signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(login.this,MainActivity.class);
            startActivity(intent);
        }
    });

    signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(login.this, com.example.myapplication.signup.class);
        }
    });

    forgotpassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(login.this, com.example.myapplication.forgotpassword.class);
        }
    });
    }
}
