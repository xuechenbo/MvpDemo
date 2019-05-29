package com.kahuanbao.com.mvp.contract;

import com.kahuanbao.com.mvp.activity.BaseView;

import retrofit2.Call;

public interface MainMvpContract {

    interface View extends BaseView {
        void success(String msg);
    }
    interface  Presenter{
        void getData();
    }

    interface Model {
        Call login();
        String log();
    }
}
