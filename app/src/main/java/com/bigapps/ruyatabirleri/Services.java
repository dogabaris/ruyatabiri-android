package com.bigapps.ruyatabirleri;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.Callback;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by shadyfade on 14.09.2016.
 */
public interface Services {

    @POST("/user/register")
        void Register(@Body pojoUser user, Callback<pojoRegister> cb);

    @POST("/user/login")
        void Login(@Body pojoUser user, Callback<pojoLogin> cb);

    @POST("/dream/")
        void createDream(@Body pojoCreateDream createDream, Callback<pojoDream> cb);

    @GET("/dream/all/{pageid}")
        void getHottestDreams(@Path("pageid") int pageid);

    @GET("{user_id}")//get user by id
    void GetUser();

    @PUT("/")//update user
        void UpdateUser();

    @DELETE("/{user_id}")
        void DeleteUser();

    @PUT("/chage-password")
        void ChangePassword();

    @GET("/all")
        void ListUser();
}
