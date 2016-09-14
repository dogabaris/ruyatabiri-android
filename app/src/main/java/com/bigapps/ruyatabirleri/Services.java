package com.bigapps.ruyatabirleri;

import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.Callback;
/**
 * Created by shadyfade on 14.09.2016.
 */
public interface Services {

    @POST("/user/register")
        void Register(@Body pojoUser user, Callback<pojoRegister> cb);

    @POST("/user/login")
        void Login(@Body pojoUser user, Callback<pojoLogin> cb);
}
