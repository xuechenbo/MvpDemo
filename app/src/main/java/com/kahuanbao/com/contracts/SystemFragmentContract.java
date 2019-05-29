package com.kahuanbao.com.contracts;

import com.kahuanbao.com.model.SystemMoudle;
import com.kahuanbao.com.model.SystemMoudleDetaails;
import com.kahuanbao.com.presenter.BasePresenter;
import com.kahuanbao.com.presenter.BaseView;

import java.util.ArrayList;
import java.util.List;

public interface SystemFragmentContract {
    interface View extends BaseView{
        void successData(List<SystemMoudle> list);

        void successDataDetails(SystemMoudleDetaails list);
    }
    interface Presenter extends BasePresenter<SystemFragmentContract.View>{
        void requesetData();


        void requestDateDetails(int page,String cid);


        void onRefresh(String cid);

        void onLoadMore(String cid);
    }
}
