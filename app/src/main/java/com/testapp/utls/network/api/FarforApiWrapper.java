package com.testapp.utls.network.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created on 17.01.2017.
 */

public class FarforApiWrapper {
    private volatile static FarforApiWrapper instance;

    private FarforApi farforApi;

    private FarforApiWrapper() {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ufa.farfor.ru/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();
        farforApi = retrofit.create(FarforApi.class);
    }

    public static FarforApiWrapper getInstance() {
        if (instance == null) {
            synchronized (FarforApiWrapper.class) {
                if (instance == null) {
                    instance = new FarforApiWrapper();
                }
            }
        }
        return instance;
    }

    public static FarforApi getFarforApi() {
        return getInstance().farforApi;
    }

}
