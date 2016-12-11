package com.kitsyambocka.etsyclient.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsyambocka.etsyclient.App;
import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.adapters.GoodsAdapter;
import com.kitsyambocka.etsyclient.models.goods.Goods;
import com.kitsyambocka.etsyclient.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchResultActivity extends BaseActivity {

    private static final int SPAN_COUNT = 3;

    @BindView(R.id.recycler_search_result)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_search_result)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.text_nothing_to_show)
    TextView tvNothing;



    @OnClick(R.id.fab)
    void onClickSearch() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setViews() {

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setViews();
                refreshLayout.setRefreshing(false);
            }
        });

        showProgressDialog();
        App.getApiManager().getGoodsByTag(getIntent().getStringExtra(Constants.CATEGORY), getIntent().getStringExtra(Constants.TAG))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Goods>() {
                    @Override
                    public final void onCompleted() {

                    }

                    @Override
                    public final void onError(Throwable e) {
                        hideProgressDialog();
                        Toast.makeText(SearchResultActivity.this, R.string.error_download, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public final void onNext(Goods response) {
                        if (response.getResults().size() == 0) {
                            tvNothing.setVisibility(View.VISIBLE);
                        } else {
                            tvNothing.setVisibility(View.INVISIBLE);
                            setUpRecycler(response);
                        }
                        hideProgressDialog();

                    }
                });
    }


    private void setUpRecycler(Goods goods) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        GoodsAdapter goodsAdapter = new GoodsAdapter(goods, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(goodsAdapter);
    }


    @Override
    public int getLayout() {
        return R.layout.activity_search_result;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
