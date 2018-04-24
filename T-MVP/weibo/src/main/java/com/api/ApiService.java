package com.api;

import com.app.annotation.apt.ApiFactory;
import com.model.Statuses;


import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/3/23.
 */
@ApiFactory
public interface ApiService {


//    statuses/home_timeline	access_token 获取当前登录用户及其所关注用户的最新微博
//    statuses/user_timeline	access_token 获取用户发布的微博
//    statuses/repost_timeline	access_token id 返回一条原创微博的最新转发微博
//    statuses/mentions	access_token 获取@当前用户的最新微博
//    statuses/show	access_token id  根据ID获取单条微博信息

//    statuses/count	access_token ids  批量获取指定微博的转发数评论数
//    statuses/go	uid id 根据ID跳转到单条微博页


    @GET("statuses/home_timeline")
    Flowable<Statuses> statuses_home_timeline(@Query("access_token") String access_token );

    @GET("statuses/user_timeline")
    Flowable<Statuses> statuses_user_timeline(@Query("access_token") String access_token );

    @GET("statuses/repost_timeline")
    Flowable<Statuses> statuses_repost_timeline(@Query("access_token") String username, @Query("id") String id);

    @GET("statuses/mentions")
    Flowable<Statuses> statuses_mentions(@Query("access_token") String access_token  );

    @GET("statuses/show")
    Flowable<Statuses> statuses_show(@Query("access_token") String access_token, @Query("id") String id);

    @GET("statuses/count")
    Flowable<Statuses> statuses_count(@Query("access_token") String access_token, @Query("ids") String ids);

    @GET("statuses/go")
    Flowable<Statuses> statuses_go(@Query("uid") String uid, @Query("id") String id);


//
//    @POST("users")
//    Flowable<CreatedResult> createUser(@Body _User user);
//
//    @GET("users")
//    Flowable<DataArr<_User>> getAllUser(@Query("skip") int skip, @Query("limit") int limit);
//
//    @GET("classes/Image")
//    Flowable<DataArr<ImageInfo>> getAllImages(@Query("where") String where, @Query("skip") int skip, @Query("limit") int limit, @Query("order") String order);
//
//    @POST("classes/Image")
//    Flowable<CreatedResult> createArticle(@Body Image mArticle);
//
//    @GET("classes/Comment")
//    Flowable<DataArr<CommentInfo>> getCommentList(@Query("include") String include, @Query("where") String where, @Query("skip") int skip, @Query("limit") int limit);
//
//    @POST("classes/Comment")
//    Flowable<CreatedResult> createComment(@Body Comment mComment);
//
//
//    @Headers("Content-Type: image/png")
//    @POST("files/{name}")
//    Flowable<CreatedResult> upFile(@Path("name") String name, @Body RequestBody body);
//
//
//    @PUT("users/{uid}")
//    Flowable<CreatedResult> upUser(@Header("X-LC-Session") String session, @Path("uid") String uid, @Body Face face);
//
//
//    @POST("classes/Message")
//    Flowable<CreatedResult> createMessage(@Body Message mComment);
//
//    @GET("classes/Message")
//    Flowable<DataArr<MessageInfo>> getMessageList(@Query("include") String include, @Query("where") String where, @Query("skip") int skip, @Query("limit") int limit, @Query("order") String order);

}
