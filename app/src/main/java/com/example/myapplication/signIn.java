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
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class
signIn extends AppCompatActivity {


    private TextInputLayout txtinputemail, txtinputpass;
    Button btnSignin;
    TextView txtSignup, txtForgotpassword;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
  //  ImageView imgGoogle;


    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(signIn.this,MainActivity.class));
            finish();
        }
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        //  getSupportActionBar().setTitle("Login");
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://goodsment-default-rtdb.firebaseio.com/");
        btnSignin = findViewById(R.id.btnSignin);
        txtinputemail = (TextInputLayout) findViewById(R.id.txtinputemail);
        txtinputpass =(TextInputLayout) findViewById(R.id.txtinputpass);
        txtSignup = findViewById(R.id.txtSingup);
        txtForgotpassword = findViewById(R.id.txtForgotpassword);
    //    imgGoogle = findViewById(R.id.imgGoogle);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtinputemail.getEditText().getText().toString();
                String password = txtinputpass.getEditText().getText().toString();
                String em = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                String pass = "^(?=.*[0-9])(?=.*[A-Z])(?=.* [@#$%^&+=!])(?=\\S+$).{4,}$";

                if (!email.isEmpty()) {
                    txtinputemail.setError(null);
                    txtinputemail.setErrorEnabled(false);
                    if (!password.isEmpty()){
                        txtinputpass.setError(null);
                        txtinputpass.setErrorEnabled(false);
                        if (email.matches(em)) {
                            txtinputemail.setError(null);
                            txtinputemail.setErrorEnabled(false);
                            if (password.length() >8) {
                                txtinputpass.setError(null);
                                txtinputpass.setErrorEnabled(false);

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
                            else {
                                txtinputpass.setError("Password must be 8 Digits!");
                            }
                        }
                        else {
                            txtinputemail.setError("Please! Enter Valid Email Address");
                        }
                    }
                    else {
                        txtinputpass.setError("Please! Enter Password");
                    }
                }
                else {
                    txtinputemail.setError("Please! Enter Email Address");
                }
            }
        });



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




//        imgGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(signIn.this,GoogleSignInActivity.class);
//                startActivity(intent);
//            }
//        });
