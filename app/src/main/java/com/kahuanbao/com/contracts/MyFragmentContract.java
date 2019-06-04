package com.kahuanbao.com.contracts;

import com.kahuanbao.com.presenter.BasePresenter;
import com.kahuanbao.com.presenter.BaseView;

public interface MyFragmentContract {
    interface View extends BaseView {
        void successData();

        void LoginSuccess();


        void ExitSuccess();
    }

    interface Presenter extends BasePresenter<MyFragmentContract.View> {
        void requestData();


        void Loginrequest(String phone,String pwd);


        void Exitrequeset();
    }
}
