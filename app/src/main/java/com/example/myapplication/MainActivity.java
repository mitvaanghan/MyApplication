package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.ui.homeFragment;
import com.example.myapplication.ui.labourManagFragment;
import com.example.myapplication.ui.privacyPolicyFragment;
import com.example.myapplication.ui.reportFragment;
import com.example.myapplication.ui.returnPolicyFragment;
import com.example.myapplication.ui.userFragment;
import com.example.myapplication.ui.vehicleOwnerFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.blue));

      Toolbar toolbar = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

        drawerLayout =findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new homeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
       }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new homeFragment()).commit();
                break;

            case R.id.nav_vOwner:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new vehicleOwnerFragment()).commit();
                break;

            case R.id.nav_user:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new userFragment()).commit();
                break;

            case R.id.nav_lmanagement:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new labourManagFragment()).commit();
                break;

            case R.id.nav_pPolicy:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new privacyPolicyFragment()).commit();
                break;

            case R.id.nav_rPolicy:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new returnPolicyFragment()).commit();
                break;

            case R.id.nav_report:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new reportFragment()).commit();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout Successfully..", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, signIn.class));
                finish();
            break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
}