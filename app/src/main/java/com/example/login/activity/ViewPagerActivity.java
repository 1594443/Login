package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.login.R;
import com.example.login.adpter.ViewPagerAdapter;
import com.example.login.databinding.ActivityViewPagerBinding;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    ActivityViewPagerBinding activityViewPagerBinding;
    private List<View> views;
    private int[] guideImages = {R.drawable.photo1, R.drawable.photo2, R.drawable.photo3,
            R.drawable.photo4, R.drawable.photo5, R.drawable.photo6};
    private ImageView[] points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityViewPagerBinding = ActivityViewPagerBinding.inflate(getLayoutInflater());
        setContentView(activityViewPagerBinding.getRoot());

        views = new ArrayList<>();
        initData();
    }

    private void initData() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        for (int i : guideImages) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(i);
            imageView.setLayoutParams(layoutParams);
            views.add(imageView);
        }
        initPoint();
        activityViewPagerBinding.viewpager.setAdapter(new ViewPagerAdapter(views));
        activityViewPagerBinding.viewpager.setOnPageChangeListener(this);
    }

    private void initPoint() {
        points = new ImageView[guideImages.length];
        //全部圆点初始化为未选中状态
        for (int i = 0; i < activityViewPagerBinding.llPoint.getChildCount(); i++) {
            points[i] = (ImageView) activityViewPagerBinding.llPoint.getChildAt(i);
            points[i].setImageResource(R.drawable.icon_point2);
            //不用滑动就能点击圆点切换图片
            points[i].setOnClickListener(this);
            points[i].setTag(i);
        }
        int index = 0;
        points[index].setImageResource(R.drawable.icon_point1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //当新的页面选中时调用
    @Override
    public void onPageSelected(int position) {
        //全部圆点初始化为未选中状态
        for (int i = 0; i < activityViewPagerBinding.llPoint.getChildCount(); i++) {
            points[i] = (ImageView) activityViewPagerBinding.llPoint.getChildAt(i);
            points[i].setImageResource(R.drawable.icon_point2);
            //必须滑动才能点击圆点切换图片
            // points[i].setOnClickListener(this);
            // points[i].setTag(i);
        }
        points[position].setImageResource(R.drawable.icon_point1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int i = (Integer) v.getTag();
        activityViewPagerBinding.viewpager.setCurrentItem(i);
    }
}