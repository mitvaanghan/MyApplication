package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class signIn extends AppCompatActivity {


    EditText edtEmail, edtPassword;
    Button btnSignin;
    TextView txtSignup, txtForgotpassword;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
  //  ImageView imgGoogle;


//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(mAuth.getCurrentUser()!=null){
//            startActivity(new Intent(signIn.this,MainActivity.class));
//            finish();
//        }
//        super.onStart();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://goodsment-default-rtdb.firebaseio.com/");
        btnSignin = findViewById(R.id.btnSignin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        txtSignup = findViewById(R.id.txtSingup);
        txtForgotpassword = findViewById(R.id.txtForgotpassword);
    //    imgGoogle = findViewById(R.id.imgGoogle);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                //Authentication
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(signIn.this, MainActivity.class));
                            Toast.makeText(signIn.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(signIn.this, "Login Failed..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

//        imgGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(signIn.this,GoogleSignInActivity.class);
//                startActivity(intent);
//            }
//        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signIn.this, signup.class));
            }
        });

        txtForgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signIn.this, forgotpassword.class));
            }
        });

    }
}
