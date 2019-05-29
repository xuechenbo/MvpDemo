package com.kahuanbao.com.abother.newnetwork;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MyCallback <T extends HttpResult> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.raw().code() == 200) {
            if(response.body().getCode() == 0){
                onSuc(response);
            }
            else if (response.body().getCode() == 1){

            }
            else if (response.body().getCode() == 2){
                onAutoLogin();
            } else {
                onFail(response.body().getMsg());
            }

        } else {//失败响应
            onFailure(call, new RuntimeException("response error,detail = " + response.raw().toString()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {//网络问题会走该回调
        if(t instanceof SocketTimeoutException){
            //
        }else if(t instanceof ConnectException){
            //
        }else if(t instanceof RuntimeException){
            //
        }
        onFail(t.getMessage());
    }

    public abstract void onSuc(Response<T> response);

    public abstract void onFail(String message);

    public abstract void onAutoLogin();

}
