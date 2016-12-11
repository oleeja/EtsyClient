package com.kitsyambocka.etsyclient.network;

import com.kitsyambocka.etsyclient.models.categories.Categories;
import com.kitsyambocka.etsyclient.models.goods.Goods;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Developer on 09.12.2016.
 */

public interface ApiService {

    @GET("taxonomy/categories")
    Observable <Categories> getCategories(@Query("api_key") String key);

    @GET("listings/{id}")
    Observable <Goods> getGoodsById(@Path("id") long id, @Query("api_key") String key, @Query("includes") String includes);

    @GET("listings/active")
    Observable <Goods> getGoodsByTag(@Query("api_key") String key, @Query("category") String category, @Query("keywords") String keywords, @Query("includes") String includes);

}
