package com.kahuanbao.com.presenter;

import android.content.Context;
import android.util.Log;

import com.kahuanbao.com.contracts.ProjectListFragmentContract;
import com.kahuanbao.com.model.BaseResp;
import com.kahuanbao.com.model.ProjectListBean;
import com.kahuanbao.com.network.RetrofitNet;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProjectListFragmentPresenter implements ProjectListFragmentContract.Presenter {
    ProjectListFragmentContract.View view;
    Context context;
    private int currentPage;
    boolean flag=true;
    public ProjectListFragmentPresenter(Context context,ProjectListFragmentContract.View view) {
        this.context=context;
        this.view=view;
    }

    @Override
    public void requestData(int i,String cid) {
        view.showDialogPress();
        this.currentPage=i;
        RetrofitNet.getInstance(context).netRequest().getProjectList(i,cid)
                .subscribeOn(Schedulers.io())//制定调度器  被观察者线程
                .observeOn(AndroidSchedulers.mainThread())   //线程调度   观察者线程
                .subscribe(new Observer<BaseResp<ProjectListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseResp<ProjectListBean> projectListBeanBaseResp) {
                       if (projectListBeanBaseResp.getErrorCode()==0){
                           view.successData(projectListBeanBaseResp.data,flag);
                       }else{
                           view.requestError(projectListBeanBaseResp.errorMsg);
                       }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError",e.toString());
                    }

                    @Override
                    public void onComplete() {
                        view.dissDialogPress();
                    }
                });
    }

    @Override
    public void onRefresh(String cid) {
        flag=true;
        requestData(1,cid);
    }

    @Override
    public void onLoadMore(String cid) {
        flag=false;
        currentPage++;
        requestData(currentPage,cid);
    }

    @Override
    public void attachView(ProjectListFragmentContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }
}
