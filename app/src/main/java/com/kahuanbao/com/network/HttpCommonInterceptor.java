package com.kahuanbao.com.network;

import android.util.Log;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2019/3/28.
 *
 */

public class HttpCommonInterceptor implements Interceptor {
    private Map<String,String> mHeaderParamsMap=new HashMap<>();
    public HttpCommonInterceptor(){}
    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.d("HttpCommonInterceptor","add common params");
        Request oldbuilder = chain.request();
        Request.Builder requestBuilder = oldbuilder.newBuilder();
        requestBuilder.method(oldbuilder.method(),oldbuilder.body());
        if (mHeaderParamsMap.size()>0){
            for (Map.Entry<String,String> params:mHeaderParamsMap.entrySet()){
                requestBuilder.header(params.getKey(),params.getValue());
            }
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {
        HttpCommonInterceptor mHttpCommonInterceptor;
        public Builder(){
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }
        public  Builder addHeaderParams(String str,String value){
            mHttpCommonInterceptor.mHeaderParamsMap.put(str,value);
            return this;
        }
        public HttpCommonInterceptor build(){
            return mHttpCommonInterceptor;
        }
    }
}
