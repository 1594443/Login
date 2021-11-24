package com.example.login.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.example.login.databinding.ActivitySpinnerBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpinnerActivity extends AppCompatActivity {

    ActivitySpinnerBinding activitySpinnerBinding;
    String[] cities, browser;
    ArrayAdapter<String> spinner1;
    SimpleAdapter spinner2;
    List<Map<String, Object>> list = new ArrayList<>();
    String item1;
    Map<String, Object> map1, map2, map3, map4, map5, item2;

    {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        map3 = new HashMap<>();
        map4 = new HashMap<>();
        map5 = new HashMap<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySpinnerBinding = ActivitySpinnerBinding.inflate(getLayoutInflater());
        setContentView(activitySpinnerBinding.getRoot());

        initSpinner1();
        initSpinner2();
    }

    public void initSpinner1() {
        //1.上下文 2.item 3.内容
        cities = new String[]{"北京", "上海", "广州"};
        spinner1 = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cities);
        activitySpinnerBinding.spinner1.setAdapter(spinner1);

        activitySpinnerBinding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item1 = spinner1.getItem(position);
                activitySpinnerBinding.tvSpinner1.setText(item1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void initSpinner2() {
        //1.上下文 2.list 3.item 4.list名称 5.list值要绑定的View
        map1.put("icon", R.mipmap.ic_launcher);
        map1.put("text", "谷歌");
        list.add(map1);
        map2.put("icon", R.mipmap.ic_launcher);
        map2.put("text", "百度");
        list.add(map2);
        map3.put("icon", R.mipmap.ic_launcher);
        map3.put("text", "搜狗");
        list.add(map3);
        map4.put("icon", R.mipmap.ic_launcher);
        map4.put("text", "360");
        list.add(map4);
        map5.put("icon", R.mipmap.ic_launcher);
        map5.put("text", "必应");
        list.add(map5);
        browser = new String[]{"百度", "搜狗", "360", "必应", "必应"};
        spinner2 = new SimpleAdapter(this, list, R.layout.my_spinner_item,
                new String[]{"icon", "text"}, new int[]{R.id.iv, R.id.tv});
        activitySpinnerBinding.spinner2.setAdapter(spinner2);

        activitySpinnerBinding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item2 = (Map<String, Object>) spinner2.getItem(position);
                activitySpinnerBinding.tvSpinner2.setText((CharSequence) item2.get("text"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
