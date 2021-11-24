package com.example.login.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.example.login.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding activitySplashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(activitySplashBinding.getRoot());

        //判断是否是第一次进入程序
        SharedPreferences sp = getSharedPreferences("First", MODE_PRIVATE);
        boolean user_first = sp.getBoolean("first", true);
        if (user_first) {
            popups();//弹窗
        } else {
            splash();//动画
        }
    }

    private void popups() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("协议")
                .setMessage("是否是第一次进入程序")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sp = getSharedPreferences("First", MODE_PRIVATE);
                        sp.edit().putBoolean("first", false).apply();
                        splash();//动画
                        dialog.dismiss();
                    }
                }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();//退出
                        dialog.dismiss();
            }
        }).create().show();
    }

    private void splash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);
    }
}
