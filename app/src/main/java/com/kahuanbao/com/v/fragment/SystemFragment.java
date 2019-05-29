package com.kahuanbao.com.v.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kahuanbao.com.R;
import com.kahuanbao.com.adapter.MyAdapter;
import com.kahuanbao.com.adapter.MyVerticalAdapter;
import com.kahuanbao.com.adapter.TagAdapter;
import com.kahuanbao.com.contracts.SystemFragmentContract;
import com.kahuanbao.com.model.SystemMoudle;
import com.kahuanbao.com.model.SystemMoudleDetaails;
import com.kahuanbao.com.presenter.SystemFragmentPresenter;
import com.kahuanbao.com.v.activity.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/3/30.
 */

public class SystemFragment extends BaseFragment implements SystemFragmentContract.View, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    TextView back;
    Unbinder unbinder;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.v_recyclerView)
    RecyclerView vRecyclerView;
    private FragmentActivity activity;
    private SystemFragmentPresenter systemFragmentPresenter;
    List<SystemMoudle> listBean;
    private LinearLayoutManager linearLayoutManager;
    private MyVerticalAdapter myVerticalAdapter;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager1;
    private List<SystemMoudleDetaails.DatasBean> datas;

    private int post;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.system_fragment, null);
        Log.e("System", "SystemFragment");
        Log.e("测试提交", "SystemFragment");
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        listBean = new ArrayList<>();
        datas = new ArrayList<>();
        titleName.setText("体系");
        back.setVisibility(View.GONE);
        linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        myVerticalAdapter = new MyVerticalAdapter(R.layout.system_item,listBean);
        vRecyclerView.setLayoutManager(linearLayoutManager);
        vRecyclerView.setAdapter(myVerticalAdapter);
        myVerticalAdapter.setOnItemClickListener(this);


        linearLayoutManager1 = new LinearLayoutManager(activity);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager1);
        myAdapter = new MyAdapter(R.layout.system_item, datas);
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemChildClickListener(this);

        systemFragmentPresenter = new SystemFragmentPresenter(activity, this);
        systemFragmentPresenter.requesetData();
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.tv_title:
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url",datas.get(position).getLink());
                intent.putExtra("title",datas.get(position).getTitle());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        initBackground(position);
        systemFragmentPresenter.requestDateDetails(0, listBean.get(position).getChildren().get(0).getId() + "");
    }

    private void initBackground(int position) {
        listBean.get(post).setFlag(false);
        myVerticalAdapter.notifyItemChanged(post);
        listBean.get(position).setFlag(true);
        post=position;
        myVerticalAdapter.notifyItemChanged(position);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.e("hidden", "SystemFragment");
        }
    }

    @Override
    public void successData(List<SystemMoudle> list) {
        Log.e("successData", "SystemFragment");
        listBean = list;
        post=0;
        listBean.get(post).setFlag(true);
        myVerticalAdapter.setNewData(listBean);
        systemFragmentPresenter.requestDateDetails(0, listBean.get(0).getChildren().get(0).getId() + "");
    }

    @Override
    public void successDataDetails(SystemMoudleDetaails list) {
        datas = list.getDatas();
        myAdapter.setNewData(datas);
    }

    @Override
    public void requestError(String reason) {

    }

    @Override
    public void showDialogPress() {
        showProgress("加载中", false);
    }

    @Override
    public void dissDialogPress() {
        hideProgress();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
