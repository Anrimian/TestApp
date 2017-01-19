package com.testapp.utls.network.api;

import com.testapp.data.InfoResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created on 16.01.2017.
 */

public interface FarforApi {

    @GET("getyml/")
    Observable<InfoResponse> getData(@Query("key") String key);
}
