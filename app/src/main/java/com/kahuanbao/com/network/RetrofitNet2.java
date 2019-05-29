package com.kahuanbao.com.network;


import com.kahuanbao.com.BuildConfig;
import com.kahuanbao.com.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.kahuanbao.com.utils.Constant.Android_URL;


/**
 * Created by Administrator on 2018/8/31.
 *
 */

public class RetrofitNet2 {
    // 网络请求超时时间值(s)
    private static final int DEFAULT_TIMEOUT = 60;
    private static RetrofitNet2 sMovieRetrofit;
    private final RequestApi mApi;

    public static RetrofitNet2 getInstance() {
        if (sMovieRetrofit == null) {
            synchronized (RetrofitNet2.class) {
                if (sMovieRetrofit == null) {
                    sMovieRetrofit = new RetrofitNet2();
                }
            }
        }
        return sMovieRetrofit;
    }

    //其他走这个
    private RetrofitNet2() {
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置网络请求超时时间
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);   //读操作超时时间
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);  //写操作超时时间
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//链接超时时间
        // 失败后尝试重新请求
        builder.retryOnConnectionFailure(false);

        //builder.addInterceptor(new AddCookiesInterceptor(getContext()));    //为请求添加cookie
        //builder.addInterceptor(new SaveCookiesInterceptor(getContext()));  //保存Cookie

        if (BuildConfig.DEBUG) {//发布版本不再打印  debugb版本打印   日志拦截器
            // 日志显示级别
            HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
            //新建log拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    LogUtil.e("test", "OkHttp====Message:" + message);
                }
            });
            loggingInterceptor.setLevel(level);
            //OkHttp进行添加拦截器loggingInterceptor
            builder.addInterceptor(loggingInterceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())   //okhttp配置
                .baseUrl(Android_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApi = retrofit.create(RequestApi.class);
    }


    public RequestApi getApi() {
        return mApi;
    }


}
