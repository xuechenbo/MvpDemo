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

import com.kahuanbao.com.R;
import com.kahuanbao.com.abother.view.Other;
import com.kahuanbao.com.network.RetrofitNet;
import com.kahuanbao.com.utils.ViewUtils;
import com.kahuanbao.com.v.activity.CollectionActivity;
import com.tencent.mmkv.MMKV;

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

public class MineFragment extends Fragment {
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
    }

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
                requestDataexit();
                ViewUtils.makeToast(getActivity(), "wolegequ", 1200);
                break;
            case R.id.ll_myScWeb:
                requestWeb();
                break;
            case R.id.ll_yejianmoshi:
                //clearCookie(getActivity());
                break;
        }
    }

    private void requestWeb() {
        RetrofitNet.getInstance(getActivity()).netRequest().collectionWeb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Log.e("WEB", string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
        HashMap<String, String> map = new HashMap<>();
        map.put("username", loginPhone.getText().toString().trim());
        map.put("password", loginPwd.getText().toString().trim());
        RetrofitNet.getInstance(activity).netRequest().login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            JSONObject jsonObject = new JSONObject(string);
                            String errorCode = jsonObject.getString("errorCode");
                            if (errorCode.equals("0")) {
                                main.setVisibility(View.GONE);
                                llMyMessage.setVisibility(View.VISIBLE);
                                name.setText(loginPhone.getText().toString().trim());
                                ViewUtils.makeToast(activity, "登录成功", 1200);
                                if (mmkv == null) {
                                    mmkv = MMKV.mmkvWithID("appMessage");
                                }
                                mmkv.encode("name", loginPhone.getText().toString().trim());//用户名

                            } else {
                                ViewUtils.makeToast(activity, jsonObject.getString("errorMsg"), 1200);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void requestDataexit() {
        RetrofitNet.getInstance(activity).netRequest().exit()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            JSONObject jsonObject = new JSONObject(string);
                            String errorCode = jsonObject.getString("errorCode");
                            if (errorCode.equals("0")) {
                                main.setVisibility(View.VISIBLE);
                                llMyMessage.setVisibility(View.GONE);
                                MMKV.mmkvWithID("appMessage").clearAll();
                                clearCookie(activity);
                            } else {
                                ViewUtils.makeToast(activity, jsonObject.getString("errorMsg"), 1200);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
