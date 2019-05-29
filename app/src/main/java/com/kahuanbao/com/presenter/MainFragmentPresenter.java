package com.kahuanbao.com.presenter;

import android.content.Context;

import com.kahuanbao.com.contracts.MainFragmentContract;
import com.kahuanbao.com.model.ArticleBean;
import com.kahuanbao.com.model.BaseResp;
import com.kahuanbao.com.network.RetrofitNet;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2019/4/10.
 *
 */

public class MainFragmentPresenter implements MainFragmentContract.Presenter{
    MainFragmentContract.View view;
    private boolean hasRefresh = true;
    private int currentPage;
    Context context;
    public MainFragmentPresenter(Context context,MainFragmentContract.View view) {
        this.context=context;
        this.view=view;
    }

    @Override
    public void attachView(MainFragmentContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }

    @Override
    public void requestBanner(int id) {

    }

    @Override
    public void requestArticle(int id) {
        this.currentPage=id;
        RetrofitNet.getInstance(context).netRequest().getArticleList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<ArticleBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResp<ArticleBean> articleBean) {
                        if(articleBean.getErrorCode() == 0){
                            view.ArticleOk(articleBean.getData(),hasRefresh);
                        }else {
                            //view.Fails(listBaseResp.getErrorMsg());
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

    @Override
    public void onRefresh() {
        hasRefresh = true;
        requestArticle(0);
    }

    @Override
    public void onLoadMore() {
        hasRefresh = false;
        currentPage ++;
        requestArticle(currentPage);
    }
}
