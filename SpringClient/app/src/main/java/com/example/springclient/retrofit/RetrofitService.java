package com.example.springclient.retrofit;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private Retrofit retrofit;

    public RetrofitService(){
        intitializeRetrofit();
    }

    private void intitializeRetrofit() {
        retrofit=new Retrofit.Builder().
                baseUrl("https://employeemanagement-production-2d55.up.railway.app")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public Retrofit getRetrofit(){
       return retrofit;
    }
}
