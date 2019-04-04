package com.bawei.yaolinga.contract;

/**
 * @Author：lenovo
 * @E-mail： 15001194794@163.com
 * @Date：2019/4/4 9:53
 * @Description：描述信息
 */
public interface ContractInterface {
    public interface ViewInterface{
        public void toRefresh(Object obj);
    }
    public interface PresenterInterface{
        public void toModel();
        public void toLogin(String phone,String pwd);
        public void toRegister(String phone,String pwd);

    }
}
