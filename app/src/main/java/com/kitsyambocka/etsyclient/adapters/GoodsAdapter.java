package com.kitsyambocka.etsyclient.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.activities.DetailActivity;
import com.kitsyambocka.etsyclient.models.goods.Goods;
import com.kitsyambocka.etsyclient.models.goods.ResultGoods;
import com.kitsyambocka.etsyclient.utils.Constants;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Developer on 10.12.2016.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    private List <ResultGoods> mGoods;
    private Context mContext;

    public GoodsAdapter (Goods goods, Context context) {
        mGoods = goods.getResults();
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.textView.setText(Html.fromHtml(mGoods.get(position).getTitle()).toString());

                Picasso.with(mContext)
                        .load(mGoods.get(position).getMainImage().getUrl_170x135())
                        .into(holder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed

                                Picasso.with(mContext)
                                        .load(mGoods.get(position).getMainImage().getUrl_170x135())
                                        .networkPolicy(NetworkPolicy.OFFLINE)
                                        .into(holder.imageView, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Toast.makeText(mContext, R.string.error_download, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constants.LISTING_ID, mGoods.get(position).getListingId());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mGoods.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.list_image)
        ImageView imageView;
        @BindView(R.id.text_title)
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}

