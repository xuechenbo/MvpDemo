package com.kahuanbao.com.mvp.presenter;


import com.kahuanbao.com.mvp.contract.MainMvpContract;
import com.kahuanbao.com.mvp.model.MainMvpModel;
import com.kahuanbao.com.mvp.presenter.BasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMvpPresenter extends BasePresenter<MainMvpContract.View> implements MainMvpContract.Presenter{

    private final MainMvpModel mainMvpModel;

    public MainMvpPresenter() {
        mainMvpModel = new MainMvpModel();
    }

    @Override
    public void getData() {
        if (!isViewAttached()) {
            return;
        }
        mainMvpModel.login().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                mView.success(response.toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                mView.success("失败了");
            }
        });
    }
}
