package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.login.databinding.ActivityFragmentBinding;

public class FragmentActivity extends AppCompatActivity {

    ActivityFragmentBinding activityFragmentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFragmentBinding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(activityFragmentBinding.getRoot());
    }
}