package com.kitsyambocka.etsyclient.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.fragments.FavouritesFragment;
import com.kitsyambocka.etsyclient.fragments.SearchFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 08.12.2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final static int NUM_ITEMS = 2;

    private List<String> mTitles;

    private Context mContext;

    private SearchFragment searchFragment;
    private FavouritesFragment favouritesFragment;


    public ViewPagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        mContext = context;
        mTitles = new ArrayList<>();
        initFragments();
        addTitles();

    }

    private void initFragments() {
        searchFragment = SearchFragment.createNewInstance();
        favouritesFragment = FavouritesFragment.createNewInstance();

    }

    private void addTitles() {
        mTitles.add(mContext.getString(R.string.title_search));
        mTitles.add(mContext.getString(R.string.title_favourites));

    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return searchFragment;
            case 1:
                return favouritesFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }


}
