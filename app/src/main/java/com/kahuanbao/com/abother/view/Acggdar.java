package com.kahuanbao.com.abother.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kahuanbao.com.R;
import com.kahuanbao.com.model.AreaBean;
import com.kahuanbao.com.model.AreaMultiBean;
import com.kahuanbao.com.model.ProvinceBean;
import com.kahuanbao.com.v.BaseActivity;
import com.kahuanbao.com.view.RPagerSnapHelper;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/3/29.
 *
 */

public class Acggdar extends BaseActivity {
    @BindView(R.id.bt4)
    Button bt4;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private OptionsPickerView optionsPicker;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private List<AreaMultiBean> mList = new ArrayList<>();
    private Myadaterp1 myadaterp;
    private LinearLayoutManager linearLayoutManager;
    private RPagerSnapHelper rPagerSnapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_item1);
        ButterKnife.bind(this);
        getOptionData();
        initData();
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rPagerSnapHelper = new RPagerSnapHelper();
        rPagerSnapHelper.attachToRecyclerView(recycler);

        rPagerSnapHelper.setOnPageListener(new RPagerSnapHelper.OnPageListener() {
            @Override
            public void onPageSelector(int fromPosition, int toPosition) {
                super.onPageSelector(fromPosition, toPosition);
                int currentPosition = rPagerSnapHelper.getCurrentPosition();
                Log.e("TAG~~~",currentPosition+"==============");
                //有-1情况
                Toast.makeText(context, "@@@~~~~~"+currentPosition, Toast.LENGTH_SHORT).show();
            }
        });

        recycler.setLayoutManager(linearLayoutManager);
        //myadaterp = new Myadaterp(R.layout.item, mList);
        myadaterp = new Myadaterp1(mList);
        recycler.setAdapter(myadaterp);

        //应该也可以的 有-1情况需判断
     /*   recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                    Log.e("TAG",lastCompletelyVisibleItemPosition+"==============");
                    //有-1情况
                    Toast.makeText(context, "@@@"+lastCompletelyVisibleItemPosition, Toast.LENGTH_SHORT).show();
                }
            }
        });*/


     myadaterp.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
         @Override
         public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
             switch (view.getId()){
                 case R.id.delete:
                     Toast.makeText(context, "删除"+mList.get(position).getDivisionName(), Toast.LENGTH_SHORT).show();
                     break;
                 case R.id.add:
                     Toast.makeText(context, "添加"+mList.get(position).getDivisionName(), Toast.LENGTH_SHORT).show();
                     break;
                 case R.id.update:
                     Toast.makeText(context, "修改"+mList.get(position).getDivisionName(), Toast.LENGTH_SHORT).show();
                     break;
             }
         }
     });
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            AreaMultiBean areaBean = new AreaMultiBean();
            areaBean.setDivisionName(i+"号标题");
            if (i==0 || i==3){
                areaBean.setItemType(1);
            }else{
                areaBean.setItemType(2);
            }
            mList.add(areaBean);
        }
    }


    @OnClick({ R.id.bt4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt4:
                optionsPicker = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {

                    }
                })
                        .setTitleText("请选择时间")
                        .setContentTextSize(20)
                        .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                            @Override
                            public void onOptionsSelectChanged(int options1, int options2, int options3) {
                                String tx = options1Items.get(options1).getPickerViewText();
                                bt4.setText(tx);
                            }
                        })
                        .build();
                optionsPicker.setPicker(options1Items);
                optionsPicker.show();
                break;
        }
    }



    private void getOptionData() {

        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */

        //选项1
        options1Items.add(new ProvinceBean(0, "广东", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "湖南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(2, "广西", "描述部分", "其他数据"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        options2Items_02.add("株洲");
        options2Items_02.add("衡阳");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("桂林");
        options2Items_03.add("玉林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);

        /*--------数据源添加完毕---------*/
    }

    class Myadaterp1 extends BaseMultiItemQuickAdapter<AreaMultiBean,BaseViewHolder> {
        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public Myadaterp1(List<AreaMultiBean> data) {
            super(data);
            addItemType(1, R.layout.item);
            addItemType(2, R.layout.item1);
        }

        @Override
        protected void convert(BaseViewHolder helper, AreaMultiBean item) {
            switch (helper.getItemViewType()) {
                case 1:
                    helper.setText(R.id.tv, item.getDivisionName());
                    helper.addOnClickListener(R.id.delete)
                            .addOnClickListener(R.id.add);
                    break;
                case 2:
                    helper.setText(R.id.tv1, item.getDivisionName());
                    helper.addOnClickListener(R.id.update);
                    break;
            }
        }
    }

}
