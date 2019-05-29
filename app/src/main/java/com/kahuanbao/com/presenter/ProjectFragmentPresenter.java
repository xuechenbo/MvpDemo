package com.kahuanbao.com.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.kahuanbao.com.contracts.ProjectFragmentContract;
import com.kahuanbao.com.model.BaseResp;
import com.kahuanbao.com.model.ProjectTreeData;
import com.kahuanbao.com.network.RetrofitNet;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ProjectFragmentPresenter implements ProjectFragmentContract.Presenter{

    ProjectFragmentContract.View view;
    Context context;

    public ProjectFragmentPresenter(Context context,ProjectFragmentContract.View view) {
        this.context=context;
        this.view=view;
    }

    @Override
    public void requestData() {

        RetrofitNet.getInstance(context).netRequest().getProjectTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            JSONObject jsonObject = new JSONObject(string);
                            if (jsonObject.getString("errorCode").equals("0")){
                                String data = jsonObject.getString("data");
                                List<ProjectTreeData> list = new Gson().fromJson(data, new TypeToken<List<ProjectTreeData>>() {
                                }.getType());
                                view.successData(list);
                            }else{
                                view.requestError(jsonObject.getString("errorMsg"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.requestError("请求错误");
                        Log.e("onError",e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete","onComplete");
                    }
                });
    }


    @Override
    public void attachView(ProjectFragmentContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }

}
