package com.kahuanbao.com.network;

import android.content.Context;

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
 * Created by Administrator on 2019/3/30.
 *
 *  登录时用netRequestLogin  保存添加
 *  其他用netRequest    添加
 *
 */

public class RetrofitNet {
    private Context context;
    private static RetrofitNet sMovieRetrofit;
    private final static int DEFAULT_TIMEOUT = 30;
    private RetrofitNet(Context context){
        this.context=context;
    }
    public static RetrofitNet getInstance(Context context){
        if (sMovieRetrofit == null) {
            synchronized (RetrofitNet.class) {
                if (sMovieRetrofit == null) {
                    sMovieRetrofit = new RetrofitNet(context);
                }
            }
        }
        return sMovieRetrofit;
    }

    public RequestApi netRequest() {
        // 创建一个OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置网络请求超时时间
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);   //读操作超时时间
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);  //写操作超时时间
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//链接超时时间
        // 失败后尝试重新请求
        builder.retryOnConnectionFailure(false);

        builder.addInterceptor(new SaveCookiesInterceptor(context));  //保存Cookie
        builder.addInterceptor(new AddCookiesInterceptor(context));    //为请求添加cookie

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
        return retrofit.create(RequestApi.class);
    }
}
