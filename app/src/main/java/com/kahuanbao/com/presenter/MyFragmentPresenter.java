package com.kahuanbao.com.presenter;

import android.content.Context;
import com.kahuanbao.com.contracts.MyFragmentContract;
import com.kahuanbao.com.network.RetrofitNet;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MyFragmentPresenter implements MyFragmentContract.Presenter{
    private Context context;
    private MyFragmentContract.View view;

    public MyFragmentPresenter(Context context,MyFragmentContract.View view) {
        this.context=context;
        this.view=view;
    }

    @Override
    public void requestData() {

    }

    //登录
    @Override
    public void Loginrequest(String phone,String pwd) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", phone);
        map.put("password", pwd);
        RetrofitNet.getInstance(context).netRequest().login(map)
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
                            String errorCode = jsonObject.getString("errorCode");
                            if (errorCode.equals("0")) {
                                view.LoginSuccess();
                            } else {
                                view.requestError(jsonObject.getString("errorMsg"));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
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


    //退出登录
    @Override
    public void Exitrequeset() {
        RetrofitNet.getInstance(context).netRequest().exit()
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
                            String errorCode = jsonObject.getString("errorCode");
                            if (errorCode.equals("0")) {
                                view.ExitSuccess();
                            } else {
                                view.requestError(jsonObject.getString("errorMsg"));
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
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
    public void attachView(MyFragmentContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }
}
