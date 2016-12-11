package com.kitsyambocka.etsyclient.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kitsyambocka.etsyclient.R;

import butterknife.ButterKnife;

/**
 * Created by Developer on 09.12.2016.
 */

public abstract class BaseActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.please_wait));

        setViews();
    }

    public void showProgressDialog(){
        if(progressDialog!=null){
            progressDialog.show();
        }
    }

    public void hideProgressDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    public abstract void setViews();

    public abstract int getLayout();
}
