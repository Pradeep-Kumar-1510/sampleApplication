package com.example.sampleapplication.apiPackage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("postData")
    Call<List<ApiDataClass>> getPosts();


    @POST("postData")
    @FormUrlEncoded
    Call<ApiDataClass> createPost(
            @Field("userId") String userId,
            @Field("title") String title,
            @Field("text") String text
    );

    @DELETE("postData/{userId}")
    Call<Void>deletePost(@Path("userId") String id);

}
