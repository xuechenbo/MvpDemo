package com.kahuanbao.com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kahuanbao.com.R;

/**
 *
 * 自定义组合标题栏
 * Created by Administrator on 2019/2/13.
 *
 */

public class TitleLayout extends LinearLayout {

    private TextView title_name;
    private TextView back;
    private TextView other;
    private CustomCallBack customCallBack;
    private OtherCallBack otherCallBack;

    //当不需要使用xml声明或者不需要使用inflate动态加载时候，实现此构造函数即可
    public TitleLayout(Context context) {
        super(context);
    }

    //当需要在xml中声明此控件，则需要实现此构造函数。并且在构造函数中把自定义的属性与控件的数据成员连接起来。
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.titlelayout, this);
        title_name = findViewById(R.id.title_name);
        back = findViewById(R.id.back);
        other = findViewById(R.id.other);

        //建立联系
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TitleLayout);
        String title_nam = array.getString(R.styleable.TitleLayout_Title_Name);
        String back_nam = array.getString(R.styleable.TitleLayout_Back_Name);
        String other_nam = array.getString(R.styleable.TitleLayout_Other_Name);
        Boolean backvisibl = array.getBoolean(R.styleable.TitleLayout_Back_Visible, false);
        Boolean othervisibl = array.getBoolean(R.styleable.TitleLayout_Other_Visible, false);

        if (backvisibl){
            back.setVisibility(GONE);
        }else{
            back.setVisibility(VISIBLE);
        }
        if (othervisibl){
            other.setVisibility(GONE);
        }else{
            other.setVisibility(VISIBLE);
        }

        title_name.setText(title_nam);
        back.setText(back_nam);
        other.setText(other_nam);

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customCallBack != null) {
                    customCallBack.onclick(v);
                }
            }
        });
        other.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otherCallBack != null) {
                    otherCallBack.onclick(v);
                }
            }
        });

    }
    public void setCustomCallBack(CustomCallBack customCallBack) {
        this.customCallBack = customCallBack;
    }

    public interface CustomCallBack {
        void onclick(View v);
    }


    public void setOtherCallBack(OtherCallBack customCallBack) {
        this.otherCallBack = customCallBack;
    }

    public interface OtherCallBack {
        void onclick(View v);
    }




}
