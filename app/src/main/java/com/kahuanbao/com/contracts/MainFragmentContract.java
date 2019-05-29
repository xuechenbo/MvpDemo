package com.kahuanbao.com.contracts;

import com.kahuanbao.com.model.ArticleBean;
import com.kahuanbao.com.presenter.BasePresenter;
import com.kahuanbao.com.presenter.BaseView;

/**
 * Created by Administrator on 2019/4/10.
 *
 */

public interface MainFragmentContract {

    interface View extends BaseView {
        void BannerOk();
        void ArticleOk(ArticleBean articleBean,boolean isRefresh);

        void ArticleFail(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void requestBanner(int id);

        void requestArticle(int id);

        void onRefresh();

        void onLoadMore();
    }
}
