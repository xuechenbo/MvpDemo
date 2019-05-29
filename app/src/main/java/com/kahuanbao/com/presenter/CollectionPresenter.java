package com.kahuanbao.com.presenter;

import android.content.Context;

import com.kahuanbao.com.contracts.CollectionContract;
import com.kahuanbao.com.model.BaseResp;
import com.kahuanbao.com.model.CoolectionBean;
import com.kahuanbao.com.network.RetrofitNet;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/4/9.
 *
 */

public class CollectionPresenter implements CollectionContract.Presenter{

    CollectionContract.View view;
    Context context;
    private int currentPage;
    private boolean hasRefresh = true;
    public CollectionPresenter(CollectionContract.View view,Context context) {
        this.view=view;
        this.context=context;
    }

    @Override
    public void onRefresh() {
        hasRefresh = true;
        requestDate(0);
    }

    @Override
    public void onLoadMore() {
        hasRefresh = false;
        currentPage ++;
        requestDate(currentPage);
    }

    @Override
    public void attachView(CollectionContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }

    @Override
    public void requestDate(int id) {
        this.currentPage=id;
        RetrofitNet.getInstance(context).netRequest().collection(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<CoolectionBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseResp<CoolectionBean> listBaseResp) {
                        if(listBaseResp.getErrorCode() == 0){
                            view.Success(listBaseResp.getData(),hasRefresh);
                        }else {
                            view.Fails(listBaseResp.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
