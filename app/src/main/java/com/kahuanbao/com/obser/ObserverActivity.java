package com.kahuanbao.com.obser;

import android.os.Bundle;
import android.util.Log;
import com.kahuanbao.com.R;
import com.kahuanbao.com.v.BaseActivity;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ObserverActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observerlayout);
        //initData();
        initData1();
        initData2();
    }

    /**
     * 链式调用
     */
    private void initData2() {
        Observable.just("链式调用").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    private void initData() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.e("initData",s);
            }
        };
        Observable.just("abcdeft").subscribe(consumer);
    }


    private void initData1() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("initData1","onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.e("initData1",s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("initData1","onError");
            }

            @Override
            public void onComplete() {
                Log.e("initData1","onComplete");
            }
        };
        //被观察者
        Observable.just("Hellow World").subscribe(observer);
    }

}
