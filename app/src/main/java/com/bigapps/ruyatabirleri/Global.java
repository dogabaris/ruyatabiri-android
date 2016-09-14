package com.bigapps.ruyatabirleri;


import android.text.TextUtils;

import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by shadyfade on 14.09.2016.
 */
public class Global {

    private static Services service;

    public static Services getService() {
        if(service == null) {
            GsonBuilder gsonBuilder = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .registerTypeAdapter(Date.class, new UnixTimeConverter());

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://139.59.148.12:8181/api")
                    .setConverter(new GsonConverter(gsonBuilder.create()))
                    .build();
            service = restAdapter.create(Services.class);
        }
        return service;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}
