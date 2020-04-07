package com.us.srs.net.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.us.srs.config.ApiStore;
import com.us.srs.config.NetConstant;
import com.us.srs.net.handle.converter.DeGsonConverterFactory;
import com.us.srs.net.intercepter.HeaderIntercepter;
import com.us.srs.net.intercepter.HttpIntercepter;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


public class HttpEngine {
    private int DEFAULT_TIMEOUT = 12;
    public static Retrofit retrofit;
    private OkHttpClient.Builder builder;
    private static ApiStore apiStore;
    private HttpEngine() {
        initOkHttp();
        initRetrofit();
        apiStore = retrofit.create(ApiStore.class);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(DeGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(NetConstant.BASE_URL)
                .build();
    }

    private void initOkHttp() {
        HeaderIntercepter headerIntercepter = new HeaderIntercepter();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        StethoInterceptor stethoInterceptor = new StethoInterceptor();
        builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(headerIntercepter)
                .addInterceptor(new HttpIntercepter())
                .addNetworkInterceptor(stethoInterceptor);
    }
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (HttpEngine.class) {
                if (retrofit == null) {
                    new HttpEngine();
                }
            }
        }
        return retrofit;
    }

    public static ApiStore getApiServer() {
        if (apiStore == null) {
            synchronized (HttpEngine.class) {
                if (apiStore == null) {
                    apiStore = getRetrofit().create(ApiStore.class);
                }
            }
        }
        return apiStore;
    }

}
