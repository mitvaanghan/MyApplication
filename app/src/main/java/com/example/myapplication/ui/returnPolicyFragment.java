package com.example.myapplication.ui;

import static com.example.myapplication.MainActivity.toolbar;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class returnPolicyFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_return_policy, container, false);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Refund & Return Policy");
        }

        return  view;
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