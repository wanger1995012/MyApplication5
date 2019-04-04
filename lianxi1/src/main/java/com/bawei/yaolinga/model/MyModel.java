package com.bawei.yaolinga.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.bawei.yaolinga.bean.Car;
import com.bawei.yaolinga.util.Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 9:56
 * @Description：描述信息
 */
public class MyModel {
    MyCallBack myCallBack;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int type = msg.arg1;
            if(type ==1){
                String json = (String) msg.obj;
                try {
                    JSONObject object = new JSONObject(json);
                    String message = object.getString("message");
                    myCallBack.success(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                String json = (String) msg.obj;
                Gson gson = new Gson();
                Car car = gson.fromJson(json, Car.class);
                myCallBack.success(car);
            }

        }
    };

    public void toLogin(String phone,String pwd){
        Utils.MyInterceptor().doPost("http://172.17.8.100/small/user/v1/login", phone, pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.obj = json;
                message.arg1 = 1;
                handler.sendMessage(message);
            }
        });
    }

    public void toRequest(MyCallBack myCallBack){
        this.myCallBack = myCallBack;
        Utils.MyInterceptor().doGet("http://172.17.8.100/ks/product/getCarts?uid=51", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.obj = json;
                handler.sendMessage(message);
            }
        });
    }

    public interface MyCallBack{
        public void success(Object obj);
    }
}
