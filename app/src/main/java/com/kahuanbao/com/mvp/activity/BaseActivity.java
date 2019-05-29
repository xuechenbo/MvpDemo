package com.kahuanbao.com.mvp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.jaeger.library.StatusBarUtil;
import com.kahuanbao.com.R;
import com.kahuanbao.com.utils.ActivityManager;
import com.kahuanbao.com.v.APP;
import com.kahuanbao.com.v.Operation;
import com.kahuanbao.com.view.DialogPress;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    //共通操作
    private Operation mBaseOperation = null;
    public Activity context;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        unbinder = ButterKnife.bind(this);
        mBaseOperation = new Operation(this);
        context = this;
        //添加到栈中
        ActivityManager.getInstance().add(this);
        setStatusBar();
        initView();
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化视图
     */
    public abstract void initView();


    /**
     * 状态栏
     */
    public void setStatusBar() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.status_color),0);
    }

    /**
     * Context
     * @return
     */
    @Override
    public APP getApplicationContext() {
        // TODO Auto-generated method stub
        return (APP) super.getApplicationContext();
    }

    /**
     * 加载进度
     * @param message
     */
    public void showProgress(String message) {
        showProgress(message, false);
    }

    public void showProgress(String message, boolean cancelable) {
        try {
            if (progressDialog == null) {
                progressDialog =new DialogPress(this, R.style.CustomDialog);
                progressDialog.setMessage(message);
                progressDialog.setCancelable(cancelable);
                progressDialog.show();
            } else {
                progressDialog.setMessage(message);
                progressDialog.setCancelable(cancelable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideProgress() {
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
                progressDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ButterKnife
     */
    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
