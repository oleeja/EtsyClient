package com.kitsyambocka.etsyclient.models.goods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 10.12.2016.
 */

public class Goods {

    private long count;

    private List<ResultGoods> results = new ArrayList<>();

    private Params params;

    private Pagination pagination;

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<ResultGoods> getResults() {
        return results;
    }

    public void setResults(List<ResultGoods> results) {
        this.results = results;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
