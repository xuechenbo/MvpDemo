package com.kahuanbao.com.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.kahuanbao.com.model.ProjectTreeData;
import com.kahuanbao.com.v.fragment.ProjectListFragment;

import java.util.List;

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    private SparseArray<ProjectListFragment> fragmentSparseArray = new SparseArray<>();
    List<ProjectTreeData> mProjectTreeData;
    public MyViewPagerAdapter(FragmentManager fm, List<ProjectTreeData> mProjectTreeData) {
        super(fm);
        this.mProjectTreeData=mProjectTreeData;
    }

    @Override
    public Fragment getItem(int position) {
        ProjectListFragment projectListFragment = fragmentSparseArray.get(position);
        if (projectListFragment != null) {
            return projectListFragment;
        } else {
            Bundle bundle = new Bundle();
            bundle.putInt("child", mProjectTreeData.get(position).getId());
            projectListFragment = ProjectListFragment.newInstance(bundle);
            fragmentSparseArray.put(position, projectListFragment);
            return projectListFragment;
        }
    }

    @Override
    public int getCount() {
        return mProjectTreeData == null ? 0 : mProjectTreeData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mProjectTreeData.get(position).getName();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }
}
