package com.kitsyambocka.etsyclient;

import android.app.Application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.kitsyambocka.etsyclient.database.DBManager;
import com.kitsyambocka.etsyclient.network.ApiManager;
import com.squareup.picasso.Picasso;

/**
 * Created by Developer on 09.12.2016.
 */

public class App extends Application {

    private static App context;
    private static ApiManager apiManager;
    private DBManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        dataManager = new DBManager(this);
        context = this;
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }

    public static ApiManager getApiManager() {
        if (apiManager == null) {
            apiManager = new ApiManager();
            apiManager.init();
        }
        return apiManager;
    }

    public static App getInstance() {
        return context;
    }

    public DBManager getDataManager() {
        return dataManager;
    }
}
