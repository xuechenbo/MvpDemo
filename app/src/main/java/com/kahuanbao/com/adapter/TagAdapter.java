package com.kahuanbao.com.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.kahuanbao.com.R;
import com.kahuanbao.com.model.SystemMoudle;

import java.util.ArrayList;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class TagAdapter implements TabAdapter {
    ArrayList<SystemMoudle> list;
    Context context;
    public TagAdapter(Context context,ArrayList<SystemMoudle> list) {
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ITabView.TabBadge getBadge(int position) {
//        return new TabView.TabBadge.Builder()
////                .setBadgeNumber(list.get(position).getVisible())
////                .build();
        return null;
    }

    @Override
    public ITabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public ITabView.TabTitle getTitle(int position) {
        return new TabView.TabTitle.Builder()
                .setContent(list.get(position).getName())
                .setTextColor(ContextCompat.getColor(context, R.color.colorAccent),
                        ContextCompat.getColor(context, R.color.colorPrimaryDark))
                .build();
    }

    @Override
    public int getBackground(int position) {
        return -1;
    }
}
