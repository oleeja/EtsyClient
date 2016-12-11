package com.kitsyambocka.etsyclient.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kitsyambocka.etsyclient.models.categories.Categories;
import com.kitsyambocka.etsyclient.models.goods.Goods;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Developer on 09.12.2016.
 */

public class ApiManager {
    private final String BASE_URL = "https://openapi.etsy.com/v2/";
    private final String APIKEY = "l6pdqjuf7hdf97h1yvzadfce";
    private final String INCLUDES = "MainImage";

    private Retrofit mRetrofit;
    private ApiService retrofitService;
    private Gson mGson;

    public void init() {
        initGson();
        initRetrofit();
        initServices();

    }

    private void initGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        mGson = builder.create();
    }

    private void initServices() {
        retrofitService = mRetrofit.create(ApiService.class);
    }


    public Gson getGson() {
        return mGson;
    }

    private void initRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public Observable<Categories> getCategories() {
        return retrofitService.getCategories(APIKEY);
    }

    public Observable<Goods> getGoodsByTag(String category, String keywords, int limit, int offset) {
        return retrofitService.getGoodsByTag(APIKEY, category, keywords, INCLUDES, limit, offset);
    }


    public Observable<Goods> getGoodsById(long listing_id) {
        return retrofitService.getGoodsById(listing_id, APIKEY, INCLUDES);
    }
}
