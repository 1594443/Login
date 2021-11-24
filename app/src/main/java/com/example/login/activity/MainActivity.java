package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.login.R;
import com.example.login.databinding.ActivityMainBinding;
import com.example.login.util.ActivityCollector;
import com.example.login.util.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.login.setOnClickListener(this);
        activityMainBinding.from.setOnClickListener(this);
        activityMainBinding.spinner.setOnClickListener(this);
        activityMainBinding.imageSwitcher.setOnClickListener(this);
        activityMainBinding.viewpager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.from:
                startActivity(new Intent(this, FormActivity.class));
                break;
            case R.id.spinner:
                startActivity(new Intent(this, SpinnerActivity.class));
                break;
            case R.id.image_switcher:
                startActivity(new Intent(this, ImageSwitcherActivity.class));
                break;
            case R.id.viewpager:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            default:
                break;
        }
    }

    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            ActivityCollector.finishAll();
        } else {
            ToastUtil.showMsg(getApplicationContext(), "再次点击退出！");
            mBackPressed = System.currentTimeMillis();
        }
    }
}