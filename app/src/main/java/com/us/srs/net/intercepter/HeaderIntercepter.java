package com.us.srs.net.intercepter;

import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HeaderIntercepter implements Interceptor {
    private Headers.Builder builder;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request reques = chain.request();
        initHeader();
        Request.Builder request=reques.newBuilder().headers(builder.build());
        Response response=chain.proceed(request.build());
        return response;
    }
    private void initHeader(){
        builder=new Headers.Builder()
                .add("Content-Type","application/json;charset=UTF-8");
    }
}
