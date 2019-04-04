package com.bawei.yaolinga.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 9:02
 * @Description：描述信息
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> list;
    Context context;

    public FragmentAdapter(FragmentManager fm, List<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }


    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
