package com.example.login.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.example.login.databinding.ActivitySplashBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding activitySplashBinding;
    Timer timer;
    int time;

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

        activitySplashBinding.go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭定时器
                timer.cancel();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
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

    public void splash() {
        time = 5;
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //向handler发送状态值
                handler.sendEmptyMessage(100);
            }
        };
        //开启定时器，时间差值为1000毫秒
        timer.schedule(task, 1, 1000);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100:
                    activitySplashBinding.go.setText("跳过 " + time);
                    time--;
                    if (time < 0) {
                        //关闭定时器
                        timer.cancel();
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                        break;
                    }
            }
        }
    };
//    private void splash() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                finish();
//            }
//        }, 3000);
//    }
}
