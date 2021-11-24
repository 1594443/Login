package com.example.login.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.login.adpter.ListAdpter;
import com.example.login.databinding.ActivityFormBinding;

public class FormActivity extends AppCompatActivity {

    ActivityFormBinding activityFormBinding;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFormBinding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(activityFormBinding.getRoot());

        activityFormBinding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        activityFormBinding.recyclerview.setAdapter(new ListAdpter(this));
    }
}
