package com.kitsyambocka.etsyclient.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsyambocka.etsyclient.App;
import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.models.categories.Result;
import com.kitsyambocka.etsyclient.models.goods.Goods;
import com.kitsyambocka.etsyclient.models.goods.ResultGoods;
import com.kitsyambocka.etsyclient.utils.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.main_image)
    ImageView imageView;

    @BindView(R.id.main_title)
    TextView tvTitle;

    @BindView(R.id.main_description)
    TextView tvDescription;

    @BindView(R.id.price)
    TextView tvPrice;

    @BindView(R.id.buttonSave)
    Button bSave;


    @Override
    public void setViews() {
        Log.d("ListingIDd", getIntent().getLongExtra(Constants.LISTING_ID, 0)+"");
        App.getApiManager().getGoodsById(getIntent().getLongExtra(Constants.LISTING_ID, 0))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Goods>() {
                    @Override
                    public final void onCompleted() {

                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.d("DetailResponse", e.getMessage());
                    }

                    @Override
                    public final void onNext(Goods response) {
                        Log.d("DetailResponse", response.getResults().get(0).getTitle());
                        setUpScreen(response.getResults().get(0));

                    }
                });
    }

    private void setUpScreen(final ResultGoods results) {
        tvTitle.setText(results.getTitle());
        tvDescription.setText(results.getDescription());
        tvPrice.setText(results.getPrice()+" "+results.getCurrencyCode());

        Picasso.with(this)
                .load(results.getMainImage().getUrl_570xN())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed

                        Picasso.with(DetailActivity.this)
                                .load(results.getMainImage().getUrl_570xN())
                                .networkPolicy(NetworkPolicy.OFFLINE)
                                .into(imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                        Toast.makeText(DetailActivity.this, "Error: Internet connection", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }
}
