package com.kahuanbao.com.abother.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import com.kahuanbao.com.R;
import com.kahuanbao.com.abother.newnetwork.HttpResult;
import com.kahuanbao.com.abother.newnetwork.MyCallback;
import com.kahuanbao.com.abother.newnetwork.RetrofitNet;
import com.kahuanbao.com.v.BaseActivity;
import org.json.JSONObject;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit2GsonActivity extends BaseActivity {
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofigson);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn, R.id.btn1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                request();
                break;
            case R.id.btn1:
                request1();
                break;
        }
    }

    private void request1() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("merchantNo", "220395819056693");
        map.put("version",  "BHKB-A-1.0.0");
        map.put("agentNo", "A2019000001");
        map.put("sign", getNewMacData(map));
        JSONObject json = new JSONObject(map);
        RequestBody body= RequestBody.create(MediaType.parse("Content-Type, application/json"),json.toString());
        Log.e("json",json.toString());
        Call<HttpResult> articleList1 = RetrofitNet.getInstance().getApi().getArticleList1(body);
        articleList1.enqueue(new MyCallback<HttpResult>() {
            @Override
            public void onSuc(Response<HttpResult> response) {
                String data = response.body().getData();

                Log.e("onResponse",data);
            }

            @Override
            public void onFail(String message) {
                Log.e("onFail","message");
            }

            @Override
            public void onAutoLogin() {
                Log.e("onAutoLogin","onAutoLogin~~~~~~~~~~~");
            }
        });
    }


    //可以了
    private void request() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("merchantNo", "220395819056693");
        map.put("version",  "BHKB-A-1.0.0");
        map.put("agentNo", "A2019000001");
        map.put("sign", getNewMacData(map));
        JSONObject json = new JSONObject(map);
        RequestBody body= RequestBody.create(MediaType.parse("Content-Type, application/json"),json.toString());
        Log.e("json",json.toString());
        Call<ResponseBody> articleList1 = RetrofitNet.getInstance().getApi().getArticleList(body);
        articleList1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure","@@@@@");
            }
        });
    }


    public static <T> T jsonToBean(String json,Class<T> clazz){
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }


    public static String getNewMacData(TreeMap<String, String> parameterMap) {
        StringBuffer urlBuffer = new StringBuffer();
        for (String key : parameterMap.keySet()) {
            urlBuffer.append(parameterMap.get(key));
        }
        return Md5(urlBuffer.toString() + "21E4ACD4CD5D4619B063F40C5A454F7D");
    }
    public static String Md5(String plainText) {
        StringBuffer buf = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;


            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            //LogUtil.syso("result: " + buf.toString());//32位的加密

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}
