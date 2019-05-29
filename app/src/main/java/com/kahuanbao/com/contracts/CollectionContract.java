package com.kahuanbao.com.contracts;

import com.kahuanbao.com.model.CoolectionBean;
import com.kahuanbao.com.presenter.BasePresenter;
import com.kahuanbao.com.presenter.BaseView;

/**
 * Created by Administrator on 2019/4/9.
 *
 */

public interface CollectionContract {
    interface View extends BaseView {
        void Success(CoolectionBean collectBean , boolean isRefresh);
        void Fails(String message);
    }
    interface Presenter extends BasePresenter<View> {
        void requestDate(int id);
        void onRefresh();

        void onLoadMore();
    }
}
