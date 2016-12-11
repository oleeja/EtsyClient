package com.kitsyambocka.etsyclient.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.kitsyambocka.etsyclient.App;
import com.kitsyambocka.etsyclient.utils.Constants;
import com.kitsyambocka.etsyclient.R;
import com.kitsyambocka.etsyclient.activities.SearchResultActivity;
import com.kitsyambocka.etsyclient.models.categories.Categories;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Developer on 08.12.2016.
 */

public class SearchFragment extends BaseFragment {

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.buttonSubmit)
    Button bSubmit;

    @BindView(R.id.editText)
    EditText editText;

    private String category;

    public static SearchFragment createNewInstance() {
        SearchFragment searchFragment = new SearchFragment();
        return searchFragment;
    }

    @Override
    public int setUpLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void setUpViews() {
        bSubmit.setEnabled(false);

        final List<String> categoriesList = new ArrayList<>();
        categoriesList.add("Downloading...");
        final ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, categoriesList);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCategory);


        App.getApiManager().getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Categories>() {
                    @Override
                    public final void onCompleted() {

                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.d("MyResponse", e.getMessage());
                    }

                    @Override
                    public final void onNext(Categories response) {

                        categoriesList.clear();
                        categoriesList.add("Please, choose category...");
                        for(int i = 0; i<response.getResults().size(); i++){
                            categoriesList.add(response.getResults().get(i).getShortName());
                        }
                        adapterCategory.notifyDataSetChanged();

                    }
                });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bSubmit.setEnabled(i!=0);

                if(i!=0){
                    category = categoriesList.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @OnClick(R.id.buttonSubmit) void submit() {
        if(editText.getText().toString().equals("")){
            showSnack();
        }else {
            Intent intent = new Intent(getContext(), SearchResultActivity.class);
            intent.putExtra(Constants.TAG, editText.getText().toString());
            intent.putExtra(Constants.CATEGORY, category);
            startActivity(intent); 
        }
        
    }

    private void showSnack() {
        Snackbar snack = Snackbar.make(getView(), R.string.input_text, Snackbar.LENGTH_LONG);
        View v = snack.getView();
        v.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        TextView tv = (TextView) v.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextSize(18);
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        snack.show();
    }
}
