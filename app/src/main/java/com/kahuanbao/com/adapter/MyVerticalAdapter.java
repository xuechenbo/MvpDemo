package com.kahuanbao.com.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kahuanbao.com.R;
import com.kahuanbao.com.model.SystemMoudle;
import com.zhy.view.flowlayout.FlowLayout;

import java.util.List;

public class MyVerticalAdapter extends BaseQuickAdapter<SystemMoudle,BaseViewHolder> {
    public MyVerticalAdapter(int layoutResId, @Nullable List<SystemMoudle> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemMoudle item) {
        if (item.isFlag()){
            helper.setBackgroundColor(R.id.ll_back,Color.YELLOW);
        }else {
            helper.setBackgroundColor(R.id.ll_back,Color.WHITE);
        }
        helper.setText(R.id.tv_title,item.getName());
    }
}
