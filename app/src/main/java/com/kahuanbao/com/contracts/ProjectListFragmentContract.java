package com.kahuanbao.com.contracts;

import com.kahuanbao.com.model.ProjectListBean;
import com.kahuanbao.com.presenter.BasePresenter;
import com.kahuanbao.com.presenter.BaseView;

public interface ProjectListFragmentContract {
    interface View extends BaseView{
        void successData(ProjectListBean projectListBean,boolean flag);
    }
    interface Presenter extends BasePresenter<ProjectListFragmentContract.View> {
        void requestData(int i,String cid);

        void onRefresh(String cid);

        void onLoadMore(String cid);
    }
}
