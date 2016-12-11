package com.kitsyambocka.etsyclient.fragments;

import android.os.Bundle;

import com.kitsyambocka.etsyclient.R;

/**
 * Created by Developer on 08.12.2016.
 */

public class FavouritesFragment extends BaseFragment {


    public static FavouritesFragment createNewInstance() {
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        Bundle bundle = new Bundle();
//        bundle.putLong(SEARCH, id);
//        cafeOrRestaurantFragment.setArguments(bundle);
        return favouritesFragment;
    }
    @Override
    public int setUpLayout() {
        return R.layout.fragment_favourites;
    }

    @Override
    public void setUpViews() {

    }
}
