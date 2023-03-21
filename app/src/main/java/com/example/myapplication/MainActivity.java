package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import com.example.myapplication.ui.homeFragment;
import com.example.myapplication.ui.labourManagFragment;
import com.example.myapplication.ui.privacyPolicyFragment;
import com.example.myapplication.ui.reportFragment;
import com.example.myapplication.ui.returnPolicyFragment;
import com.example.myapplication.ui.userFragment;
import com.example.myapplication.ui.vehicleOwnerFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    TextView headerEmail;
    TextView headername;
    Fragment fragment = null;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public static Toolbar toolbar;

    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        headerEmail = headerView.findViewById(R.id.nav_header_email);
        headername = headerView.findViewById(R.id.nav_header_name);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //getSupportActionBar().hide();
        getSupportActionBar().setTitle("Home");


        //headrview
        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();
        headerEmail.setText(email);
        String st = email.replaceAll("\\.",",");


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Admin/Registration");

       databaseReference.child(st).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               String name = snapshot.child("name").getValue(String.class);
               String email = snapshot.child("email").getValue(String.class);

               headername.setText(name);
               headerEmail.setText(email);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

       //headerprofile
       headerView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,adminProfile.class));
           }
       });


        navigationView.setNavigationItemSelectedListener(this);
        showHome();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }


    private void showHome() {
        fragment = new homeFragment();
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.fragment, fragment)
                    .addToBackStack(null).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    //navigationdrawer
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new homeFragment()).commit();
                break;

            case R.id.nav_vOwner:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new vehicleOwnerFragment()).commit();
                break;
            case R.id.nav_user:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new userFragment()).commit();
                break;

            case R.id.nav_lmanagement:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new labourManagFragment()).commit();
                break;

            case R.id.nav_pPolicy:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new privacyPolicyFragment()).commit();
                break;

            case R.id.nav_rPolicy:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new returnPolicyFragment()).commit();
                break;

            case R.id.nav_report:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new reportFragment()).commit();
                break;

            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to Logout ?");
                builder.setTitle("Alert !");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    mAuth.signOut();
                    startActivity(new Intent(MainActivity.this, signIn.class));
                    finish();
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
