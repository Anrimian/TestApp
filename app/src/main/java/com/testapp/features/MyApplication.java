package com.testapp.features;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.testapp.database.HelperFactory;

/**
 * Created on 18.01.2017.
 */

public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        HelperFactory.setHelper(getApplicationContext());
        MultiDex.install(MyApplication.this);
    }
    @Override
    public void onTerminate() {
        HelperFactory.releaseHelper();
        super.onTerminate();
    }
}
