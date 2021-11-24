package com.example.login.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.apkfuns.logutils.LogUtils;
import com.example.login.R;
import com.example.login.databinding.ActivityImageSwitcherBinding;

public class ImageSwitcherActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {

    ActivityImageSwitcherBinding activityImageSwitcherBinding;
    private int[] images = {R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
            R.drawable.photo4, R.drawable.photo5, R.drawable.photo6};
    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityImageSwitcherBinding = ActivityImageSwitcherBinding.inflate(getLayoutInflater());
        setContentView(activityImageSwitcherBinding.getRoot());

        activityImageSwitcherBinding.imageswitcher.setFactory(this);
        activityImageSwitcherBinding.imageswitcher.setImageResource(images[0]);

        handler.postDelayed(runnable, 3000);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            LogUtils.d("again");
            if (index != 5) {
                index++;
                activityImageSwitcherBinding.imageswitcher.setImageResource(images[index]);
                handler.postDelayed(runnable, 3000);
            } else {
                index = 0;
                activityImageSwitcherBinding.imageswitcher.setImageResource(images[index]);
                handler.postDelayed(runnable, 3000);
            }
        }
    };

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imageView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
