package com.kahuanbao.com.abother.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.kahuanbao.com.R;
import com.kahuanbao.com.v.BaseActivity;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by Administrator on 2019/2/27.
 *
 */

public class ViewPagerActivity extends BaseActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    ArrayList<Integer>mGoodsList;
    ArrayList<ImageView> mivGoodsList;
    GoodsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layotuviewpager);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //准备数据源
        mGoodsList=new ArrayList<>();
        mGoodsList.add(R.mipmap.welcom_bg);
        mGoodsList.add(R.mipmap.welcom_bg);
        mGoodsList.add(R.mipmap.welcom_bg);
        mivGoodsList=new ArrayList<>();
        for(int i=0;i<mGoodsList.size();i++){
            ImageView iv=new ImageView(this);
            iv.setImageResource(mGoodsList.get(i));
            mivGoodsList.add(iv);
        }
        mAdapter=new GoodsAdapter(this,mivGoodsList);
        vp.setOffscreenPageLimit(3);
        //页面之间的间距
        vp.setPageMargin(40);
        //动画
        vp.setPageTransformer(true,new GalleryTransformer());
        vp.setAdapter(mAdapter);
        //要显示的页
        vp.setCurrentItem(1);

    }

    //准备适配器类
    class GoodsAdapter extends PagerAdapter {
        Context context;
        ArrayList<ImageView> ivGoodsList;
        public GoodsAdapter(Context context,ArrayList<ImageView>ivGoodsList){
            this.context=context;
            this.ivGoodsList=ivGoodsList;
        }

        @Override
        public int getCount() {
            return ivGoodsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public ImageView instantiateItem(ViewGroup container, int position) {
            ImageView imageView=ivGoodsList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
