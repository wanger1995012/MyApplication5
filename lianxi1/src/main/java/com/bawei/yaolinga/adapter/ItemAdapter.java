package com.bawei.yaolinga.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.yaolinga.R;
import com.bawei.yaolinga.bean.Car;
import com.bawei.yaolinga.fragment.ShouFragment;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 9:28
 * @Description：描述信息
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {
    List<Car.DataBean.ListBean> list;
    Context context;
    ShouFragment fragment;

    public ItemAdapter(List<Car.DataBean.ListBean> list, Context context,ShouFragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout2,null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int i) {
        holder.titles.setText(list.get(i).getTitle());
        holder.prices.setText(list.get(i).getPrice()+"");
        holder.nums.setText(list.get(i).getNum()+"");
        holder.checkBox.setChecked(list.get(i).isSelect);
        Glide.with(context).load(list.get(i).getImages()).into(holder.img);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked() == false){
                    fragment.setSjCheck(i,-1,false);
                    holder.checkBox.setChecked(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        public TextView jia,jian,titles,prices,nums;
        ImageView img;
        CheckBox checkBox;
        public Holder(@NonNull View itemView) {
            super(itemView);
            prices = itemView.findViewById(R.id.text_child_price);
            titles = itemView.findViewById(R.id.text_child_title);
            nums = itemView.findViewById(R.id.text_child_number);
            jian = itemView.findViewById(R.id.text_child_jian);
            jia = itemView.findViewById(R.id.text_child_jia);
            img = itemView.findViewById(R.id.image_view_child);
            checkBox = itemView.findViewById(R.id.check_box_child);
        }
    }
}
