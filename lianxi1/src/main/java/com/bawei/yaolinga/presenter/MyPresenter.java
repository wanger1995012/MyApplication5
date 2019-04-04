package com.bawei.yaolinga.presenter;

import com.bawei.yaolinga.contract.ContractInterface;
import com.bawei.yaolinga.model.MyModel;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 9:55
 * @Description：描述信息
 */
public class MyPresenter implements ContractInterface.PresenterInterface {
    //获取M和V层
    MyModel myModel;
    ContractInterface.ViewInterface v;

    public MyPresenter(ContractInterface.ViewInterface v) {
        myModel = new MyModel();
        this.v = v;
    }

    @Override
    public void toModel() {
        myModel.toRequest(new MyModel.MyCallBack() {
            @Override
            public void success(Object obj) {
                v.toRefresh(obj);
            }
        });
    }

    @Override
    public void toLogin(String phone, String pwd) {

    }

    @Override
    public void toRegister(String phone, String pwd) {

    }
}
