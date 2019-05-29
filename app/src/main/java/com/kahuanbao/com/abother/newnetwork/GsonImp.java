package com.kahuanbao.com.abother.newnetwork;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GsonImp {
    //首页列表
    @POST("app/merchant/userCredits")
    @Headers({"Content-type:application/json;charset=UTF-8"})
    Call<ResponseBody> getArticleList(@Body RequestBody body);

    @POST("app/merchant/userCredits")
    @Headers({"Content-type:application/json;charset=UTF-8"})
    Call<HttpResult> getArticleList1(@Body RequestBody body);


    //修改密码
    @POST("request.app")
    Call<ResponseBody> FindPwd1(String map);

}
