package com.kitsyambocka.etsyclient.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.adapters.ViewPagerAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ViewPagerAdapter adapter;


    @Override
    public void setViews() {
        getSupportActionBar().setElevation(0);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
}
