package com.kahuanbao.com.network;

import com.kahuanbao.com.model.ArticleBean;
import com.kahuanbao.com.model.BaseResp;
import com.kahuanbao.com.model.CoolectionBean;
import com.kahuanbao.com.model.ProjectListBean;
import com.kahuanbao.com.model.ProjectTreeData;
import com.kahuanbao.com.model.SystemMoudle;
import com.kahuanbao.com.model.SystemMoudleDetaails;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2019/3/29.
 *
 */

public interface RequestApi {


    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<ResponseBody> login(@FieldMap HashMap<String, String> body);

    //退出登录状态
    @GET("/user/logout/json")
    Observable<ResponseBody> exit();

    //我的收藏
    @GET("lg/collect/list/{id}/json")
    Observable<BaseResp<CoolectionBean>> collection(@Path("id") int id);

    //收藏网址
    @GET("lg/collect/usertools/json")
    Observable<ResponseBody> collectionWeb();

    //首页列表
    @GET("article/list/{page}/json")
    Observable<BaseResp<ArticleBean>> getArticleList(@Path("page") int num);


    //项目分类
    @GET("project/tree/json")
    Observable<ResponseBody> getProjectTree();

    //项目分类
    @GET("project/list/{id}/json?")
    Observable<BaseResp<ProjectListBean>> getProjectList(@Path("id") int num, @Query("cid") String cid);

    //TODO  返回的数据为JSONARRAY
    //体系
    @GET("tree/json")
    Observable<BaseResp<List<SystemMoudle>>> getSystemList();

    //TODO  返回的数据为JSONObject
    //体系详情
    @GET("article/list/{id}/json?")
    Observable<BaseResp<SystemMoudleDetaails>> getSystemListDetails(@Path("id") int num, @Query("cid") String cid);

}
