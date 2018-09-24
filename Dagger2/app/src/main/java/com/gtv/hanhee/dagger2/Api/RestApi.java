package com.gtv.hanhee.dagger2.Api;

import com.gtv.hanhee.dagger2.Model.Responsitory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApi {
    @GET("/users/{user}/repos")
    Call<ArrayList<Responsitory>> getResponsitory(@Path("user") String userName);
}
