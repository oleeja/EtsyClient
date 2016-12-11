package com.kitsyambocka.etsyclient.activities;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsyambocka.etsyclient.App;
import com.kitsyambocka.etsyclient.R;
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
        showProgressDialog();
        long id = getIntent().getLongExtra(Constants.LISTING_ID, 0);
        App.getInstance().getDataManager().open();
        ResultGoods goods = App.getInstance().getDataManager().getGoodsById(id);
        App.getInstance().getDataManager().close();
        if (goods != null) {
            bSave.setText(R.string.delete);
            setUpScreen(goods);
        } else {
            App.getApiManager().getGoodsById(id)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Goods>() {
                        @Override
                        public final void onCompleted() {

                        }

                        @Override
                        public final void onError(Throwable e) {
                            hideProgressDialog();
                            Toast.makeText(DetailActivity.this, R.string.error_download, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public final void onNext(Goods response) {
                            bSave.setText(R.string.save);
                            setUpScreen(response.getResults().get(0));

                        }
                    });
        }
    }

    private void setUpScreen(final ResultGoods results) {
        tvTitle.setText(Html.fromHtml(results.getTitle()).toString());  //deprecated in Android N
        tvDescription.setText(Html.fromHtml(results.getDescription()).toString());
        tvPrice.setText(results.getPrice() + " " + results.getCurrencyCode());

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
                                        Toast.makeText(DetailActivity.this, R.string.error_download, Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

        hideProgressDialog();

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getInstance().getDataManager().open();
                if (bSave.getText().toString().equals(getResources().getString(R.string.save))) {
                    App.getInstance().getDataManager().addGoods(results);
                    bSave.setText(R.string.delete);

                } else {
                    App.getInstance().getDataManager().deleteGoodsById(results.getListingId());
                    bSave.setText(R.string.save);
                    onBackPressed();
                }

                App.getInstance().getDataManager().close();
            }
        });
    }



    @Override
    public int getLayout() {
        return R.layout.activity_detail;
    }
}
