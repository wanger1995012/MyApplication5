package com.bawei.yaolinga.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.yaolinga.R;
import com.bawei.yaolinga.adapter.MyAdapter;
import com.bawei.yaolinga.bean.Car;
import com.bawei.yaolinga.contract.ContractInterface;
import com.bawei.yaolinga.presenter.MyPresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 8:58
 * @Description：描述信息
 */
public class ShouFragment extends Fragment implements ContractInterface.ViewInterface {
    RecyclerView recyclerView;
    CheckBox checkBox;
    TextView text_price_sum;
    List<Car.DataBean> mList = new ArrayList<>();
    private MyAdapter adapter;
    ContractInterface.PresenterInterface p;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.show_fragment,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        checkBox = view.findViewById(R.id.check_box_qx);
        text_price_sum = view.findViewById(R.id.text_price_sum);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //创建适配器
        adapter = new MyAdapter(mList, getContext(),this);
        recyclerView.setAdapter(adapter);
        //获取P层接口实现类
        p = new MyPresenter(this);
        p.toModel();
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQX();
            }
        });

    }

    public void setSJChild(){
        for (int i = 0; i < mList.size(); i++) {
            for (int j = 0; j < mList.get(i).getList().size(); j++) {
                if(mList.get(i).isSelect){
                    mList.get(i).getList().get(j).isSelect = true;
                }
            }
        }
        total();
        adapter.notifyDataSetChanged();
    }

    private void setQX() {
        for (int i = 0; i < mList.size(); i++) {
            for (int j = 0; j < mList.get(i).getList().size(); j++) {
                if(checkBox.isChecked()){
                    setSelected(true);
                    total();
                }else{
                    setSelected(false);
                    text_price_sum.setText("￥："+0.00);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
    public void setSelected(boolean isCheck){
        for (int i = 0; i < mList.size(); i++) {
            mList.get(i).isSelect = isCheck;
            for (int j = 0; j < mList.get(i).getList().size(); j++) {
                mList.get(i).getList().get(j).isSelect = isCheck;
            }
        }
    }

    public void setSjCheck(int i ,int tag , boolean isCheck){
        mList.get(i).isSelect = isCheck;
        if(tag != -1){
            mList.get(i).getList().get(tag).isSelect = false;

        }else{
            for (int j = 0; j < mList.get(i).getList().size(); j++) {
                mList.get(i).getList().get(j).isSelect = isCheck;
            }
        }
        total();
        adapter.notifyDataSetChanged();
    }



    public void total(){
        int prices = 0;
        for (int i = 0; i < mList.size(); i++) {
            for (int j = 0; j < mList.get(i).getList().size(); j++) {
                if(mList.get(i).getList().get(j).isSelect){
                    int num = mList.get(i).getList().get(j).getNum();
                    int price = (int)mList.get(i).getList().get(j).getPrice();
                    int nums = num*price;
                    prices += mList.get(i).getList().get(j).getPrice() + nums;
                }
            }
        }
        text_price_sum.setText("￥："+prices+"");
    }

    @Override
    public void toRefresh(Object obj) {
        Car car = (Car) obj;
        List<Car.DataBean> list = car.getData();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
