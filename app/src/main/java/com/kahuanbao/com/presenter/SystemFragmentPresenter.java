package com.kahuanbao.com.presenter;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kahuanbao.com.contracts.SystemFragmentContract;
import com.kahuanbao.com.model.BaseResp;
import com.kahuanbao.com.model.SystemMoudle;
import com.kahuanbao.com.model.SystemMoudleDetaails;
import com.kahuanbao.com.network.RetrofitNet;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class SystemFragmentPresenter implements SystemFragmentContract.Presenter {


    Context context;
    SystemFragmentContract.View view;
    private int currentPage;
    boolean flag=true;
    private ArrayList<SystemMoudleDetaails> list;

    public SystemFragmentPresenter(Context context, SystemFragmentContract.View view) {
        this.context=context;
        this.view=view;

    }

    @Override
    public void requesetData() {
        view.showDialogPress();
        RetrofitNet.getInstance(context).netRequest().getSystemList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<List<SystemMoudle>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseResp<List<SystemMoudle>> responseBody) {
                        if (responseBody.getErrorCode()==0){
                            view.successData(responseBody.getData());
                        }else{
                            view.requestError(responseBody.getErrorMsg());
                        }
                    }
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        view.dissDialogPress();
                    }
                });
    }


    //条目详情
    @Override
    public void requestDateDetails(int page,String cid) {
        this.currentPage=page;
        view.showDialogPress();
        RetrofitNet.getInstance(context).netRequest().getSystemListDetails(currentPage,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResp<SystemMoudleDetaails>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(BaseResp<SystemMoudleDetaails> resp) {
                        if (resp.getErrorCode()==0){
                            view.successDataDetails(resp.getData());
                        }else{
                            view.requestError(resp.getErrorMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

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
        requestDateDetails(0,cid);
    }

    @Override
    public void onLoadMore(String cid) {
        flag=false;
        currentPage++;
        requestDateDetails(currentPage,cid);
    }

    @Override
    public void attachView(SystemFragmentContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }
}
