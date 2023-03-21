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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.myapplication.R;

public class privacyPolicyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_privacy_policy, container, false);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Privacy Policy");
        }

        WebView webView = view.findViewById(R.id.privacypolicy);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.freeprivacypolicy.com/live/2efa1d29-9b7e-43a3-822e-6e50eff7b4a2");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
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