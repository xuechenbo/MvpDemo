package com.kahuanbao.com.abother.view;

import android.content.Intent;
import android.view.View;

import com.kahuanbao.com.R;
import com.kahuanbao.com.mvp.activity.BaseActivity;

import butterknife.OnClick;

public class Other extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.other;
    }

    @Override
    public void initView() {

    }

    @OnClick({R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6, R.id.bt7, R.id.bt8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                startActivity(new Intent(this,RecyclerView_Activity.class));
                break;
            case R.id.bt2:
                startActivity(new Intent(this,Retrofit2GsonActivity.class));
                break;
            case R.id.bt3:
                startActivity(new Intent(this,Acggdar.class));
                break;
            case R.id.bt4:
                startActivity(new Intent(this,WebviewXFiveActivity.class));
                break;
            case R.id.bt5:
                startActivity(new Intent(this,LuncherActivity.class));
                break;
            case R.id.bt6:
                startActivity(new Intent(this,OcrActivity.class));
                break;
            case R.id.bt7:
                startActivity(new Intent(this,ViewPagerActivity.class));
                break;
            case R.id.bt8:
                break;
        }
    }
}
