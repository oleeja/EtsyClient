package com.kitsyambocka.etsyclient.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsyambocka.etsyclient.App;
import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.adapters.GoodsAdapter;
import com.kitsyambocka.etsyclient.models.goods.Goods;
import com.kitsyambocka.etsyclient.models.goods.ResultGoods;
import com.kitsyambocka.etsyclient.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchResultActivity extends BaseActivity {

    private static final int AMOUNT = 24;

    private static final int SPAN_COUNT = 3;

    @BindView(R.id.recycler_search_result)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_search_result)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.text_nothing_to_show)
    TextView tvNothing;

    private int mCounter;
    private boolean mScrollFlag;
    private String category;
    private String tag;

    private ArrayList <ResultGoods> listGoods;

    private GoodsAdapter goodsAdapter;

    @OnClick(R.id.fab)
    void onClickSearch() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setViews() {
        listGoods = new ArrayList<>();
        category = getIntent().getStringExtra(Constants.CATEGORY);
        tag = getIntent().getStringExtra(Constants.TAG);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager lManager = (LinearLayoutManager) layoutManager;
                    int lastVisibleItem = lManager.findLastVisibleItemPosition();

                    if (dy>0 && lastVisibleItem == mCounter-1 && mScrollFlag) {
                        downloadData(mCounter);
                        mScrollFlag = false;
                    }
                }
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCounter = 0;
                setViews();
                refreshLayout.setRefreshing(false);
            }
        });

        setUpRecycler();

        downloadData(mCounter);

    }

    private void downloadData(int offset){
        showProgressDialog();
        App.getApiManager().getGoodsByTag(category, tag, AMOUNT, offset)
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
                            listGoods.addAll(response.getResults());
                            goodsAdapter.notifyDataSetChanged();
                            mCounter = listGoods.size();
                            mScrollFlag = true;
                        }
                        hideProgressDialog();

                    }
                });
    }

    private void setUpRecycler() {
        int startOffset = 0;
        if(listGoods.isEmpty()){
            downloadData(startOffset);
        }else {
            mCounter = listGoods.size();
            mScrollFlag = true;
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT);
        Goods goods = new Goods();
        goods.setResults(listGoods);
        goodsAdapter = new GoodsAdapter(goods, this);
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
