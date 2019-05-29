package com.kahuanbao.com.contracts;

import com.kahuanbao.com.model.ProjectTreeData;
import com.kahuanbao.com.presenter.BasePresenter;
import com.kahuanbao.com.presenter.BaseView;

import java.util.List;

public interface ProjectFragmentContract {
    interface View extends BaseView {
        void successData(List<ProjectTreeData> list);
    }

    interface Presenter extends BasePresenter<ProjectFragmentContract.View> {
        void requestData();
    }
}
