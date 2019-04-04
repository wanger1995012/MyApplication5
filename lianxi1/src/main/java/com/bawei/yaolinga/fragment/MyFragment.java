package com.bawei.yaolinga.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.yaolinga.R;
import com.bawei.yaolinga.view.ShowActivity;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 8:58
 * @Description：描述信息
 */
public class MyFragment extends Fragment {
    TextView text_login;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.my_fragment,container,false);
        text_login = view.findViewById(R.id.text_login);
        imageView = view.findViewById(R.id.image_touxiang);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ShowActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }
}
