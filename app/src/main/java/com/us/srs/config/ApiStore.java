package com.us.srs.config;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.*;

public interface ApiStore {
    @GET("stock/market/list/gainers")
    Observable<JsonArray> getMostactiveList();
    @GET("stock/market/batch")
    Observable<JsonObject> getSearChBySymbol(@Query("symbols") String symbols, @Query("types")String types);
    @GET("stock/market/batch")
    Observable<JsonObject> getNews(@Query("symbols") String symbols, @Query("types")String types,@Query("range")String range,@Query("last")String last);
    @GET("stock/{symbol}/book")
    Observable<JsonObject> getBooks(@Path("symbol")String symbol);
}
