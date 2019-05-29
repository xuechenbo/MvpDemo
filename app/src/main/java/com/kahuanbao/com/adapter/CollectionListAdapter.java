package com.kahuanbao.com.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kahuanbao.com.R;
import com.kahuanbao.com.model.CoolectionBean;
import java.util.List;


public class CollectionListAdapter extends BaseQuickAdapter<CoolectionBean.DatasBean,BaseViewHolder> {

    public CollectionListAdapter(int layoutResId, @Nullable List<CoolectionBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CoolectionBean.DatasBean item) {
        helper.setText(R.id.tv_content, item.getTitle());
    }
}
