package com.kahuanbao.com.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kahuanbao.com.R;
import com.kahuanbao.com.model.SystemMoudleDetaails;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<SystemMoudleDetaails.DatasBean,BaseViewHolder>{


    public MyAdapter(int layoutResId, @Nullable List<SystemMoudleDetaails.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemMoudleDetaails.DatasBean item) {
        helper.addOnClickListener(R.id.tv_title);
        helper.setText(R.id.tv_title,item.getTitle());
    }
}
