package com.kahuanbao.com.abother.view;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by Administrator on 2019/2/13.
 */

public class PreLoadX5Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initX5();
        preinitX5WebCore();
    }

    private void initX5() {
        QbSdk.initX5Environment(getApplicationContext(), cb);
        Log.e("haha","预加载中...");
    }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished(boolean arg0) {
            // TODO Auto-generated method stub
            Log.e("haha", " onViewInitFinished is " + arg0);
        }

        @Override
        public void onCoreInitFinished() {
            // TODO Auto-generated method stub
            Log.e("haha","预加载中...onCoreInitFinished");

        }
    };
    private void preinitX5WebCore() {

        if(!QbSdk.isTbsCoreInited()) {

            // preinit只需要调用一次，如果已经完成了初始化，那么就直接构造view
            Log.e("haha","预加载中...preinitX5WebCore");
            QbSdk.preInit(getApplicationContext(), cb);// 设置X5初始化完成的回调接口

        }
    }
}
