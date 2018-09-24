package com.gtv.hanhee.retrofit2demo.Retrofit2;

public class APIUtils {
    public static final String Base_url = "http://192.168.0.157:8080/DemoRetrofit/";

    public static DataClient getData() {
        return RetrofitClient.getClient(Base_url).create(DataClient.class);
    }
}
