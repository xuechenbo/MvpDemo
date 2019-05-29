package com.kahuanbao.com.abother.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kahuanbao.com.R;
import com.kahuanbao.com.model.RecyclerBean;
import com.kahuanbao.com.utils.CommonUtils;
import com.kahuanbao.com.v.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Administrator on 2019/4/12.
 * <p>
 * recyclerview字母索引排序
 */

public class RecyclerView_Activity extends BaseActivity {
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.titlelayout)
    RelativeLayout titlelayout;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private ArrayList<RecyclerBean> list;
    private MyAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
        initView();
    }

    private void intData(){
        getOperation().addParameter("name","abc");
        getOperation().forward(RecyclerView_Activity.class);

    }
    private void initView() {
        titleName.setText("排序");
        back.setVisibility(View.GONE);
        list = new ArrayList<>();
        initData();
        linearLayoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(R.layout.recycler_item, list);
        myAdapter.bindToRecyclerView(recycler);
        TextView text = (TextView) myAdapter.getViewByPosition(3, R.id.num);
        //嘟嘟噜度，嘟嘟噜度，嘟嘟噜度，大大大，嘟嘟噜度，嘟嘟噜度，嘟嘟噜度，大大大，哦累哇，我在东北玩泥巴，虽然东北不大，但是大连没有家。
    }

    private void initData() {
        RecyclerBean recyclerBean = new RecyclerBean();
        recyclerBean.setName("你好");
        recyclerBean.setFirstLetter(firstNum("你好"));
        recyclerBean.setPinyin(CommonUtils.getPingYin("你好"));
        RecyclerBean recyclerBean1 = new RecyclerBean();
        recyclerBean1.setName("啊啊");
        recyclerBean1.setFirstLetter(firstNum("啊啊"));
        recyclerBean1.setPinyin(CommonUtils.getPingYin("啊啊"));
        RecyclerBean recyclerBean2 = new RecyclerBean();
        recyclerBean2.setName("偶吧");
        recyclerBean2.setFirstLetter(firstNum("偶吧"));
        recyclerBean2.setPinyin(CommonUtils.getPingYin("偶吧"));
        RecyclerBean recyclerBean3 = new RecyclerBean();
        recyclerBean3.setName("正在");
        recyclerBean3.setFirstLetter(firstNum("正在"));
        recyclerBean3.setPinyin(CommonUtils.getPingYin("正在"));
        RecyclerBean recyclerBean4 = new RecyclerBean();
        recyclerBean4.setName("补办");
        recyclerBean4.setFirstLetter(firstNum("补办"));
        recyclerBean4.setPinyin(CommonUtils.getPingYin("补办"));
        RecyclerBean recyclerBean5 = new RecyclerBean();
        recyclerBean5.setName("存储");
        recyclerBean5.setFirstLetter(firstNum("存储"));
        recyclerBean5.setPinyin(CommonUtils.getPingYin("存储"));
        RecyclerBean recyclerBean6 = new RecyclerBean();
        recyclerBean6.setName("看看");
        recyclerBean6.setFirstLetter(firstNum("看看"));
        recyclerBean6.setPinyin(CommonUtils.getPingYin("看看"));
        list.add(recyclerBean);
        list.add(recyclerBean);
        list.add(recyclerBean1);
        list.add(recyclerBean1);
        list.add(recyclerBean2);
        list.add(recyclerBean2);
        list.add(recyclerBean3);
        list.add(recyclerBean3);
        list.add(recyclerBean4);
        list.add(recyclerBean4);
        list.add(recyclerBean5);
        list.add(recyclerBean5);
        list.add(recyclerBean6);
        list.add(recyclerBean6);
        Collections.sort(list);
    }

    //获取首字母
    public String firstNum(String string){
        String firstnum = CommonUtils.getPingYin(string).substring(0, 1).toUpperCase();// 获取拼音首字母并转成大写
        if (!firstnum.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
            firstnum = "#";
        }
        return firstnum;
    }
    //

    class MyAdapter extends BaseQuickAdapter<RecyclerBean,BaseViewHolder>{
        List<RecyclerBean> data;
        public MyAdapter(int layoutResId, @Nullable List<RecyclerBean> data) {
            super(layoutResId, data);
            this.data=data;
        }

        @Override
        protected void convert(BaseViewHolder helper, RecyclerBean item){
            TextView num = helper.getView(R.id.num);

            helper.setText(R.id.tv,item.getName());
            if (helper.getPosition() == 0) {
                num.setVisibility(View.VISIBLE);
                num.setText(item.getFirstLetter());
            } else if (helper.getPosition() > 0) {
                if (!item.getFirstLetter().equals(data.get(helper.getPosition()-1).getFirstLetter())) {
                    num.setVisibility(View.VISIBLE);
                    num.setText(item.getFirstLetter());
                } else {
                    num.setVisibility(View.GONE);
                }
            }
        }
    }
}
