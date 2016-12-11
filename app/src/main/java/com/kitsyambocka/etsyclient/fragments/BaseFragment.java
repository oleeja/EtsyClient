package com.kitsyambocka.etsyclient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Developer on 08.12.2016.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setUpLayout(), container, false);
        ButterKnife.bind(this, view);

        setUpViews();
        return view;
    }

    public abstract int setUpLayout();

    public abstract void setUpViews();
}
