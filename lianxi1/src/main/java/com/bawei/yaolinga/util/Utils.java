package com.bawei.yaolinga.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 9:56
 * @Description：描述信息
 */
public class Utils {
    OkHttpClient okHttpClient;
    public static Utils utils;
    private Utils(){
        okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                Log.i("tag", "request: "+request);
                Log.i("tag", "Response: "+proceed);
                return proceed;
            }
        }).build();
    }
    public static synchronized Utils MyInterceptor(){
        if(utils == null){
            utils = new Utils();
        }
        return utils;
    }

    public void doGet(String url, Callback callback){
        Request request = new Request.Builder().url(url).get().build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public void doPost(String url , String phone,String pwd,Callback callback){

        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("phone",phone);
        formBody.add("pwd",pwd);
        RequestBody body = formBody.build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);

    }
}
