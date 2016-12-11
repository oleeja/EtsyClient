package com.kitsyambocka.etsyclient.models.categories;

import java.util.List;

/**
 * Created by Developer on 09.12.2016.
 */

public class Categories {

    private long count;

    private List<Result> results = null;

    private Object params;

    private String type;

    private Object pagination;


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getPagination() {
        return pagination;
    }

    public void setPagination(Object pagination) {
        this.pagination = pagination;
    }

}
