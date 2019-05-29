package com.kahuanbao.com.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kahuanbao.com.R;
import com.kahuanbao.com.model.ArticleBean;

import java.util.List;

/**
 * Created by Administrator on 2019/4/10.
 *
 */

public class ArticleListAdapter extends BaseQuickAdapter<ArticleBean.DatasBean,BaseViewHolder>{

    public ArticleListAdapter(int layoutResId, @Nullable List<ArticleBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        helper.getView(R.id.tv_tag).setVisibility(View.GONE);
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_content, item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_author, item.getAuthor());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_time, item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getChapterName())) {
            String classifyName = item.getSuperChapterName() + " / " + item.getChapterName();
            helper.setText(R.id.tv_type, classifyName);
        }
        if (item.getSuperChapterName().contains("项目")) {
            helper.getView(R.id.tv_tag).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_tag, "项目");
            helper.setTextColor(R.id.tv_tag,mContext.getResources().getColor(R.color.green));
            helper.setBackgroundRes(R.id.tv_tag,R.drawable.drawable_shape_green);
        } else if (item.getSuperChapterName().contains("热门")) {
            helper.getView(R.id.tv_tag).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_tag, "热门");
            helper.setTextColor(R.id.tv_tag,mContext.getResources().getColor(R.color.red));
            helper.setBackgroundRes(R.id.tv_tag,R.drawable.drawable_shape_red);
        }

    }
}
