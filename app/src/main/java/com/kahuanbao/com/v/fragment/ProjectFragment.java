package com.kahuanbao.com.v.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kahuanbao.com.R;
import com.kahuanbao.com.adapter.MyViewPagerAdapter;
import com.kahuanbao.com.contracts.ProjectFragmentContract;
import com.kahuanbao.com.model.ProjectTreeData;
import com.kahuanbao.com.presenter.ProjectFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/3/30.
 */

public class ProjectFragment extends Fragment implements ProjectFragmentContract.View {

    @BindView(R.id.project_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.project_viewpager)
    ViewPager mViewPager;
    Unbinder unbinder;
    private FragmentActivity activity;
    private ProjectFragmentPresenter projectFragmentPresenter;
    private List<ProjectTreeData> mProjectTreeData;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_fragment, null);
        Log.e("Project", "ProjectFragmentPresenter");
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView title_name = view.findViewById(R.id.title_name);
        TextView back = view.findViewById(R.id.back);
        title_name.setText("项目");
        back.setVisibility(View.GONE);
        projectFragmentPresenter = new ProjectFragmentPresenter(activity, this);
        projectFragmentPresenter.requestData();
    }

    private void initData() {
        mViewPager.setAdapter(new MyViewPagerAdapter(getChildFragmentManager(),mProjectTreeData));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //取消页面切换动画
                mViewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.e("hidden", "ProjectFragmentPresenter");
            //projectFragmentPresenter.requestData();
        }
    }

    @Override
    public void successData(List<ProjectTreeData> list) {
        mProjectTreeData=list;
        initData();
    }

    @Override
    public void requestError(String reason) {

    }

    @Override
    public void showDialogPress() {

    }

    @Override
    public void dissDialogPress() {

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        if (mProjectTreeData != null) {
            mProjectTreeData.clear();
            mProjectTreeData = null;
        }
        super.onDestroyView();
    }

}
