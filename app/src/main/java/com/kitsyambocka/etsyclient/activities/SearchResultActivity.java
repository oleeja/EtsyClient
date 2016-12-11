package com.kitsyambocka.etsyclient.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kitsyambocka.etsyclient.App;
import com.kitsyambocka.etsyclient.utils.Constants;
import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.adapters.GoodsAdapter;
import com.kitsyambocka.etsyclient.models.goods.Goods;

import butterknife.BindView;
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

    private ProgressDialog progressDialog;

    @Override
    public void setViews() {

        Log.d("MyResult", getIntent().getStringExtra(Constants.TAG) + " "+getIntent().getStringExtra(Constants.CATEGORY));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setViews();
                refreshLayout.setRefreshing(false);
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please, wait...");

        progressDialog.show();
        App.getApiManager().getGoodsByTag(getIntent().getStringExtra(Constants.CATEGORY), getIntent().getStringExtra(Constants.TAG))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Goods>() {
                    @Override
                    public final void onCompleted() {

                    }

                    @Override
                    public final void onError(Throwable e) {
                        progressDialog.dismiss();
                        Log.d("MyResponse", e.getMessage());
                    }

                    @Override
                    public final void onNext(Goods response) {
                        if(response.getResults().size()==0){
                            tvNothing.setVisibility(View.VISIBLE);
                        }else {
                            tvNothing.setVisibility(View.INVISIBLE);
                            setUpRecycler(response);
                        }
                        if(progressDialog!=null){
                            progressDialog.dismiss();
                        }

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
}
