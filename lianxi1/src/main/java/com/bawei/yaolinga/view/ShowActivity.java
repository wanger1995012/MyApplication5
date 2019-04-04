package com.bawei.yaolinga.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bawei.yaolinga.R;
import com.bawei.yaolinga.contract.ContractInterface;
import com.bawei.yaolinga.presenter.MyPresenter;

public class ShowActivity extends AppCompatActivity {

    TextView text_back,text_like;
    EditText phone,pwd;
    Button but_login;
    ContractInterface.PresenterInterface p;

    public ShowActivity(ContractInterface.PresenterInterface p) {
        this.p = p;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        text_back = findViewById(R.id.text_back);
        text_like = findViewById(R.id.text_like);
        phone = findViewById(R.id.edit_phone);
        pwd = findViewById(R.id.edit_pwd);
        but_login = findViewById(R.id.but_login);
        text_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        text_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = ShowActivity.this.phone.getText().toString();
                String pwd = ShowActivity.this.pwd.getText().toString();
                //调用P层方法
                p.toLogin(phone, pwd);


            }
        });
    }
}
