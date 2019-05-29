package com.kahuanbao.com.mvp.activity;

import android.view.View;
import com.kahuanbao.com.R;
import com.kahuanbao.com.mvp.presenter.MainMvpPresenter;
import com.kahuanbao.com.mvp.contract.MainMvpContract;
import com.kahuanbao.com.utils.ViewUtils;

import butterknife.OnClick;

public class MainMvpActivity extends BaseMvpActivity<MainMvpPresenter> implements MainMvpContract.View{


    /**
     * https://www.jianshu.com/p/ae0b21d3238a
     *
     */

    private MainMvpPresenter mainMvpPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_login;
    }

    @Override
    public void initView() {
        mainMvpPresenter = new MainMvpPresenter();
        mainMvpPresenter.attachView(this);
    }

    @OnClick({R.id.login, R.id.register_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                mainMvpPresenter.getData();
                break;
            case R.id.register_text:
                break;
        }
    }

    @Override
    public void success(String msg) {
        ViewUtils.makeToast(this,msg,1200);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
