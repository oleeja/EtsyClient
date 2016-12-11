package com.kitsyambocka.etsyclient.fragments;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kitsyambocka.etsyclient.App;
import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.adapters.GoodsAdapter;
import com.kitsyambocka.etsyclient.models.goods.Goods;
import com.kitsyambocka.etsyclient.models.goods.ResultGoods;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Developer on 08.12.2016.
 */

public class FavouritesFragment extends BaseFragment {

    @BindView(R.id.recycler_favourites)
    RecyclerView recyclerView;

    @BindView(R.id.text_nothing_to_show_favourites)
    TextView tvNothing;

    private GoodsAdapter adapter;

    public static FavouritesFragment createNewInstance() {
        FavouritesFragment favouritesFragment = new FavouritesFragment();
        return favouritesFragment;
    }
    @Override
    public int setUpLayout() {
        return R.layout.fragment_favourites;
    }

    @Override
    public void setUpViews() {
        Goods goods = new Goods();

        App.getInstance().getDataManager().open();
        List<ResultGoods> resultGoods = App.getInstance().getDataManager().getGoods();
        App.getInstance().getDataManager().close();

        if(resultGoods!=null){
            tvNothing.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            goods.setResults(resultGoods);

            adapter = new GoodsAdapter(goods, getActivity());
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }else {
            tvNothing.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        setUpViews();
    }
}
