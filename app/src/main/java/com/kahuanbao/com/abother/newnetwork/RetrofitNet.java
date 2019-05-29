package com.kahuanbao.com.abother.newnetwork;

import com.kahuanbao.com.BuildConfig;
import com.kahuanbao.com.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNet {
    public static final String BASE_MAIN_URL1="http://baihujinkong.llyzf.cn:6442/posp-api/";
    private static final int DEFAULT_TIME_OUT=5;  //超时时间5秒
    private static final int DEFAULT_READ_TIME_OUT=10;
    private final Retrofit build;
    private static RetrofitNet retrofitServiceManager;

    public static RetrofitNet getInstance() {
        if (retrofitServiceManager == null) {
            synchronized (RetrofitNet.class) {
                if (retrofitServiceManager == null) {
                    retrofitServiceManager = new RetrofitNet();
                }
            }
        }
        return retrofitServiceManager;
    }

    public RetrofitNet() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间
        /*HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder() //拦截器
                .build();
        builder.addInterceptor(commonInterceptor);*/
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

        //创建Retrofit
        build = new Retrofit.Builder().client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_MAIN_URL1)
                .build();

    }
    public GsonImp getApi() {
        return build.create(GsonImp.class);
    }
}
