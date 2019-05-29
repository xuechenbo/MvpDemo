package com.kahuanbao.com.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kahuanbao.com.R;
import com.kahuanbao.com.model.ProjectListBean;

import java.util.List;

public class ProjectListAdapter extends BaseQuickAdapter<ProjectListBean.DatasBean,BaseViewHolder> {

    public ProjectListAdapter(int layoutResId, @Nullable List<ProjectListBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListBean.DatasBean item) {
        helper.setText(R.id.tv_content,item.getTitle());
    }
}
