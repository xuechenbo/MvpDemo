package com.kahuanbao.com.v;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import com.jaeger.library.StatusBarUtil;
import com.kahuanbao.com.R;
import com.kahuanbao.com.utils.ActivityManager;
import com.kahuanbao.com.view.DialogPress;


public class BaseActivity extends Activity {

    //共通操作
    private Operation mBaseOperation = null;
    public Activity context;
    /**
     * 当前Activity的弱引用，防止内存泄露
     **/
    //private WeakReference<Activity> context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mBaseOperation = new Operation(this);
        context = this;
        //添加到栈中
        ActivityManager.getInstance().add(this);

        setStatusBar();

    }

    public void setStatusBar() {
        StatusBarUtil.setColor(this,getResources().getColor(R.color.status_color),0);
    }

    @Override
    public void finish() {
        super.finish();
        if (android.os.Build.VERSION.SDK_INT >= 5) {
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (android.os.Build.VERSION.SDK_INT >= 5) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }
    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return this.mBaseOperation;
    }

    @Override
    public APP getApplicationContext() {
        // TODO Auto-generated method stub
        return (APP) super.getApplicationContext();
    }

    private ProgressDialog progressDialog;

    public void showProgress(int messageResId) {
        showProgress(getString(messageResId));
    }

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
}
