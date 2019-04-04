package com.bawei.yaolinga.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.yaolinga.R;
import com.bawei.yaolinga.bean.Car;
import com.bawei.yaolinga.fragment.ShouFragment;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 9:20
 * @Description：描述信息
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {
    List<Car.DataBean> list;
    Context context;
    ShouFragment fragment;

    public MyAdapter(List<Car.DataBean> list, Context context,ShouFragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.checkBox.setChecked(list.get(i).isSelect);
        holder.textView.setText(list.get(i).getSellerName());
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView.setLayoutManager(manager);
        //创建商家中商品的适配器
        ItemAdapter adapter = new ItemAdapter(list.get(i).getList(),context, fragment);
        holder.recyclerView.setAdapter(adapter);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    fragment.setSjCheck(i,-1,holder.checkBox.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends  RecyclerView.ViewHolder{

        public RecyclerView recyclerView;
        public CheckBox checkBox;
        public TextView textView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_view_child);
            checkBox = itemView.findViewById(R.id.check_box_sj);
            textView = itemView.findViewById(R.id.text_sj);
        }
    }
}
