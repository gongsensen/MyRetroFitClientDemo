package com.example.bawie.nsg_porjects.client;

import com.example.bawie.nsg_porjects.home.bean.HomeBean;
import com.example.bawie.nsg_porjects.home.bean.HomeBeans;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {

    @GET("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Observable<HomeBean> getHome();

    @GET("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Observable<HomeBeans> getHomes();

    @GET("{url}")
    Observable<BaseResponse<HomeBean>> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> maps
    );

    @GET
    Observable<BaseResponse<HomeBean>> executesGet(@Url String url);
    @POST("{url}")
    Observable<BaseResponse<HomeBean>> executePost(
            @Path("url") String url,
            @QueryMap Map<String, String> maps);

    @POST
    Observable<ResponseBody> json(
            @Path("url") String url,
            @Body RequestBody jsonStr);

    @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(
            @Path("url") String url,
            @Part("image\"; filename=\"image.jpg") RequestBody requestBody);

    @POST("{url}")
    Observable<ResponseBody> uploadFiles(
            @Path("url") String url,
            @Part("userName") String description,
            @PartMap() Map<String, RequestBody> maps);



}
