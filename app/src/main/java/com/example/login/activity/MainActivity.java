package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;

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

        activityMainBinding.from.setOnClickListener(this);
        activityMainBinding.spinner.setOnClickListener(this);
        activityMainBinding.imageSwitcher.setOnClickListener(this);
        activityMainBinding.viewPager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.from:
                intent = new Intent(this, FormActivity.class);
                startActivity(intent);
                break;
            case R.id.spinner:
                intent = new Intent(this, SpinnerActivity.class);
                startActivity(intent);
                break;
            case R.id.image_switcher:
                intent = new Intent(this, ImageSwitcherActivity.class);
                startActivity(intent);
                break;
            case R.id.view_pager:
                intent = new Intent(this, ViewPagerActivity.class);
                startActivity(intent);
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