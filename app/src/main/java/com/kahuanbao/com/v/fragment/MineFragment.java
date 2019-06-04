package com.kahuanbao.com.v.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.kahuanbao.com.R;
import com.kahuanbao.com.abother.view.Other;
import com.kahuanbao.com.contracts.MyFragmentContract;
import com.kahuanbao.com.network.RetrofitNet;
import com.kahuanbao.com.presenter.MyFragmentPresenter;
import com.kahuanbao.com.utils.ViewUtils;
import com.kahuanbao.com.v.activity.CollectionActivity;
import com.tencent.mmkv.MMKV;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2019/3/30.
 */

public class MineFragment extends BaseFragment implements MyFragmentContract.View {
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    TextView back;
    @BindView(R.id.other)
    TextView other;
    Unbinder unbinder;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.ib_txt_clear)
    ImageButton ibTxtClear;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.ll_myMessage)
    LinearLayout llMyMessage;
    @BindView(R.id.swit)
    Switch swit;
    private FragmentActivity activity;
    private MMKV mmkv;
    private MyFragmentPresenter myFragmentPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fragment, null);
        Log.e("Mine", "MineFragment");
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {

        swit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){

                }else{

                }
            }
        });
        titleName.setText("我的");
        back.setVisibility(View.GONE);
        other.setVisibility(View.GONE);
        MMKV.initialize(activity);
        String name1 = MMKV.mmkvWithID("appMessage").decodeString("name");
        if (!TextUtils.isEmpty(name1)) {
            main.setVisibility(View.GONE);
            llMyMessage.setVisibility(View.VISIBLE);
            name.setText(name1);
        } else {
            llMyMessage.setVisibility(View.GONE);
            main.setVisibility(View.VISIBLE);
        }
        myFragmentPresenter = new MyFragmentPresenter(getActivity(), this);

    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
        }
        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"成功了",Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(getActivity(),"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }
        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(getActivity(),"取消了",Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            Log.e("hidden", "MineFragment");
        }
    }

    @OnClick({R.id.other1, R.id.ll_yejianmoshi, R.id.ib_txt_clear, R.id.find_pwd, R.id.login, R.id.register_text, R.id.ll_mySc, R.id.ll_exit, R.id.ll_myScWeb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.other1:
                startActivity(new Intent(getActivity(), Other.class));
                break;
            case R.id.ib_txt_clear:
                break;
            case R.id.find_pwd:
                break;
            case R.id.login:
                requestDataLogin();
                break;
            case R.id.register_text:
                ViewUtils.makeToast(getActivity(), "register_text", 1200);
                break;
            case R.id.ll_mySc:
                startActivity(new Intent(getActivity(), CollectionActivity.class));
                break;
            case R.id.ll_exit:
                myFragmentPresenter.Exitrequeset();
                break;
            case R.id.ll_myScWeb:

                break;
            case R.id.ll_yejianmoshi:
                //分享一下
               /* new ShareAction(getActivity()).withText("hello")
                        .setDisplayList(SHARE_MEDIA.QZONE)
                        .setCallback(shareListener).open();*/
                new ShareAction(getActivity())
                        .setPlatform(SHARE_MEDIA.QZONE)//传入平台
                        .withText("hello")//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();

                break;
        }
    }


    private void requestDataLogin() {
        if (TextUtils.isEmpty(loginPhone.getText().toString().trim())) {
            ViewUtils.makeToast(activity, "请输入账号", 1200);
            return;
        }
        if (TextUtils.isEmpty(loginPwd.getText().toString().trim())) {
            ViewUtils.makeToast(activity, "请输入密码", 1200);
            return;
        }
        myFragmentPresenter.Loginrequest(loginPhone.getText().toString(),loginPwd.getText().toString());

    }

    @Override
    public void successData() {

    }

    @Override
    public void LoginSuccess() {
        main.setVisibility(View.GONE);
        llMyMessage.setVisibility(View.VISIBLE);
        name.setText(loginPhone.getText().toString().trim());
        ViewUtils.makeToast(activity, "登录成功", 1200);
        if (mmkv == null) {
            mmkv = MMKV.mmkvWithID("appMessage");
        }
        mmkv.encode("name", loginPhone.getText().toString().trim());//用户名
    }

    @Override
    public void ExitSuccess() {
        main.setVisibility(View.VISIBLE);
        llMyMessage.setVisibility(View.GONE);
        MMKV.mmkvWithID("appMessage").clearAll();
        clearCookie(activity);
    }

    @Override
    public void requestError(String reason) {
        ViewUtils.makeToast(getActivity(),reason,1200);
    }

    @Override
    public void showDialogPress() {
        showProgress("正在加载",false);
    }

    @Override
    public void dissDialogPress() {
        hideProgress();
    }

    /**
     * 清除本地Cookie
     *
     * @param context Context
     */
    public void clearCookie(Context context) {
        SharedPreferences sp = context.getSharedPreferences("cookies_prefs", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
