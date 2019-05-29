package com.kahuanbao.com.mvp.model;

import android.util.Log;

import com.kahuanbao.com.abother.newnetwork.RetrofitNet;
import com.kahuanbao.com.mvp.contract.MainMvpContract;

import org.json.JSONObject;

import java.util.TreeMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

import static com.kahuanbao.com.abother.view.Retrofit2GsonActivity.getNewMacData;

public class MainMvpModel implements MainMvpContract.Model {

    @Override
    public Call login() {
        //Model这一步可以省略，我脚着
        TreeMap<String, String> map = new TreeMap<>();
        map.put("merchantNo", "220395819056693");
        map.put("version",  "BHKB-A-1.0.0");
        map.put("agentNo", "A2019000001");
        map.put("sign", getNewMacData(map));
        JSONObject json = new JSONObject(map);
        RequestBody body= RequestBody.create(MediaType.parse("Content-Type, application/json"),json.toString());
        Log.e("json",json.toString());
        return RetrofitNet.getInstance().getApi().getArticleList(body);

    }


    @Override
    public String log(){

        return "";
    }

}
