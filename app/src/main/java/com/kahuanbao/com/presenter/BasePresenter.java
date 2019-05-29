package com.kahuanbao.com.presenter;

/**
 * Created by Administrator on 2019/2/15.
 *
 */

public interface BasePresenter<T extends BaseView>{
    void attachView(T view);

    void detachView();
}
