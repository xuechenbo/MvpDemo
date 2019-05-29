package com.kahuanbao.com.v.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kahuanbao.com.R;
import com.kahuanbao.com.adapter.ProjectListAdapter;
import com.kahuanbao.com.contracts.ProjectListFragmentContract;
import com.kahuanbao.com.model.ProjectListBean;
import com.kahuanbao.com.presenter.ProjectListFragmentPresenter;
import com.kahuanbao.com.utils.ViewUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.LinkedList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProjectListFragment extends BaseFragment implements ProjectListFragmentContract.View{

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    Unbinder unbinder;
    private int child;
    boolean mIsPrepare = false;		//视图还没准备好
    boolean mIsVisible= false;		//不可见
    boolean mIsFirstLoad = true;	//第一次加载
    private ProjectListFragmentPresenter projectListFragmentPresenter;
    private List<ProjectListBean.DatasBean> datas;
    private ProjectListAdapter projectListAdapter;

    public static ProjectListFragment newInstance(Bundle bundle) {
        ProjectListFragment fragment = new ProjectListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsPrepare = true;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_list, null);
        unbinder = ButterKnife.bind(this, view);
        initListener();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            mIsVisible = true;
            lazyLoad();
        } else {
            mIsVisible = false;
        }
    }
    private void lazyLoad() {
        //这里进行三个条件的判断，如果有一个不满足，都将不进行加载
        if (!mIsPrepare || !mIsVisible||!mIsFirstLoad) {
            return;
        }
        initView();
        //数据加载完毕,恢复标记,防止重复加载
        mIsFirstLoad = false;
    }

    private void initView() {
        //断言
        assert getArguments() != null;
        child = getArguments().getInt("child");
        datas = new LinkedList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(linearLayoutManager);
        projectListAdapter = new ProjectListAdapter(R.layout.item_articlelist, datas);
        recycler.setAdapter(projectListAdapter);

        projectListFragmentPresenter = new ProjectListFragmentPresenter(getActivity(), this);
        projectListFragmentPresenter.requestData(1,child+"");
    }

    private void initListener() {
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                projectListFragmentPresenter.onRefresh(child+"");
                refreshlayout.finishRefresh(1000);
            }
        });
        smart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                projectListFragmentPresenter.onLoadMore(child+"");
                refreshlayout.finishLoadmore(1000);
            }
        });
    }


    @Override
    public void successData(ProjectListBean projectListBean,boolean flag) {
        if (flag){   //下拉刷新
            datas = projectListBean.getDatas();
            projectListAdapter.replaceData(datas);
        }else{
            datas.addAll(projectListBean.getDatas());
            projectListAdapter.setNewData(datas);
        }
        if (projectListBean.getDatas().size() == 0) {
            ViewUtils.makeToast(getActivity(), "没有更多数据了", 1200);
        }
    }

    @Override
    public void requestError(String reason) {

    }

    @Override
    public void showDialogPress() {
        showProgress("加载中...",false);
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
