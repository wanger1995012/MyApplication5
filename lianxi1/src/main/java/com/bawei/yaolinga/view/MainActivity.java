package com.bawei.yaolinga.view;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bawei.yaolinga.R;
import com.bawei.yaolinga.adapter.FragmentAdapter;
import com.bawei.yaolinga.fragment.MyFragment;
import com.bawei.yaolinga.fragment.ShouFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    RadioGroup group;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = findViewById(R.id.view_pager);
        group = findViewById(R.id.radio_group);
        //创建集合添加Fragment
        List<Fragment> list = new ArrayList<>();
        list.add(new ShouFragment());
        list.add(new MyFragment());
        //创建适配器你
        adapter = new FragmentAdapter(getSupportFragmentManager(), list, this);
        pager.setAdapter(adapter);
        //pager的监听
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                group.check(group.getChildAt(i).getId());
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb1:
                        pager.setCurrentItem(0,false);
                        break;
                    case R.id.rb2:
                        pager.setCurrentItem(1,false);
                        break;
                }
            }
        });

    }
}
