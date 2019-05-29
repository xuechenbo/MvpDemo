package com.kahuanbao.com.v.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kahuanbao.com.R;
import com.kahuanbao.com.adapter.ArticleListAdapter;
import com.kahuanbao.com.contracts.MainFragmentContract;
import com.kahuanbao.com.model.ArticleBean;
import com.kahuanbao.com.presenter.MainFragmentPresenter;
import com.kahuanbao.com.utils.ViewUtils;
import com.kahuanbao.com.v.Operation;
import com.kahuanbao.com.v.activity.WebViewActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/3/30.
 *
 */

public class MainFragment extends Fragment implements MainFragmentContract.View {
    Unbinder unbinder;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.other)
    TextView other;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    private FragmentActivity activity;
    private MainFragmentPresenter mainFragmentPresenter;
    private List<ArticleBean.DatasBean> datas;
    private ArticleListAdapter articleListAdapter;
    private MZBannerView banner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, null);
        Log.e("Main", "mainFragment");
        unbinder = ButterKnife.bind(this, view);
        initView();
        initListener();
        return view;
    }


    private void initView() {
        titleName.setText("首页");
        back.setVisibility(View.GONE);
        datas = new LinkedList<>();
        ArrayList<String> list = new ArrayList<>();
        list.add("http://img2.imgtn.bdimg.com/it/u=1447362014,2103397884&fm=200&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=111342610,3492888501&fm=26&gp=0.jpg");
        list.add("http://imgsrc.baidu.com/imgad/pic/item/77094b36acaf2eddc8c37dc7861001e9390193e9.jpg");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(linearLayoutManager);
        articleListAdapter = new ArticleListAdapter(R.layout.item_articlelist, datas);
        recycler.setAdapter(articleListAdapter);
        View view = View.inflate(getActivity(), R.layout.main_fagment_banner, null);
        banner = view.findViewById(R.id.banner);
        articleListAdapter.addHeaderView(view);

        articleListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url",datas.get(position).getLink());
                intent.putExtra("title",datas.get(position).getTitle());
                startActivity(intent);
            }
        });
        banner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mainFragmentPresenter = new MainFragmentPresenter(getActivity(), this);
        mainFragmentPresenter.requestArticle(0);
    }

    private void initListener() {
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mainFragmentPresenter.onRefresh();
                refreshlayout.finishRefresh(1000);
            }
        });
        smart.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mainFragmentPresenter.onLoadMore();
                refreshlayout.finishLoadmore(1000);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
           mainFragmentPresenter.requestArticle(0);
        }
    }

    /*Viewpager配合Fragment使用*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("setUserVisibleHint", "mainFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void BannerOk() {

    }

    //列表数据
    @Override
    public void ArticleOk(ArticleBean articleBean, boolean isRefresh) {
        if (isRefresh) {
            datas = articleBean.getDatas();
            articleListAdapter.replaceData(datas);
        } else {
            datas.addAll(articleBean.getDatas());
            articleListAdapter.addData(articleBean.getDatas());
        }
        if (articleBean.getDatas().size() == 0) {
            ViewUtils.makeToast(getActivity(), "没有更多数据了", 1200);
        }
    }

    @Override
    public void ArticleFail(String message) {
        ViewUtils.makeToast(getActivity(), message, 1200);
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

    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
        @Override
        public void onBind(Context context, int i, String s) {
            // 数据绑定
            Glide.with(context).load(s).into(imageView);
        }

    }
}
