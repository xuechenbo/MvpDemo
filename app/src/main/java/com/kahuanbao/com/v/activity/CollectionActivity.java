package com.kahuanbao.com.v.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.kahuanbao.com.R;
import com.kahuanbao.com.adapter.CollectionListAdapter;
import com.kahuanbao.com.contracts.CollectionContract;
import com.kahuanbao.com.model.CoolectionBean;
import com.kahuanbao.com.presenter.CollectionPresenter;
import com.kahuanbao.com.utils.ViewUtils;
import com.kahuanbao.com.v.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.LinkedList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/4/9.
 *
 */

public class CollectionActivity extends BaseActivity implements CollectionContract.View {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.other)
    TextView other;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart)
    SmartRefreshLayout normalView;
    private CollectionPresenter collectionPresenter;
    private CollectionListAdapter adapter;
    private List<CoolectionBean.DatasBean> datasBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        initDate();
    }

    private void initDate() {
        collectionPresenter = new CollectionPresenter(this, context);
        datasBeanList = new LinkedList<>();
        titleName.setText("我的收藏");
        other.setVisibility(View.GONE);
        normalView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                collectionPresenter.onRefresh();
                refreshLayout.finishRefresh(1000);
            }
        });
        normalView.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                collectionPresenter.onLoadMore();
                refreshlayout.finishLoadmore(1000);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new CollectionListAdapter(R.layout.item_homepage,datasBeanList);
        //adapter.setOnItemClickListener(this);
        recycler.setAdapter(adapter);

        collectionPresenter.requestDate(0);

    }


    @Override
    public void Success(CoolectionBean collectBean, boolean isRefresh) {
        if(isRefresh){
            datasBeanList = collectBean.getDatas();
            adapter.replaceData(datasBeanList);
        }else {
            datasBeanList.addAll(collectBean.getDatas());
            adapter.addData(collectBean.getDatas());
        }
        if(collectBean.getDatas().size()==0){
            ViewUtils.makeToast(context,"没有更多数据了",1200);
        }
    }

    @Override
    public void Fails(String message) {
        ViewUtils.makeToast(context,message,1200);
    }

    @Override
    public void showDialogPress() {
        showProgress("加载中..");
    }

    @Override
    public void dissDialogPress() {
        hideProgress();
    }

    @Override
    public void requestError(String reason) {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
