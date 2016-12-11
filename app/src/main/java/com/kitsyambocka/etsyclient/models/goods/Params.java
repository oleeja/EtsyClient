package com.kitsyambocka.etsyclient.models.goods;

/**
 * Created by Developer on 10.12.2016.
 */

public class Params {
    private long limit;

    private long offset;

    private Object page;

    private String keywords;

    private String sort_on;

    private String sort_order;

    private String category;

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSort_on() {
        return sort_on;
    }

    public void setSort_on(String sort_on) {
        this.sort_on = sort_on;
    }

    public String getSort_order() {
        return sort_order;
    }

    public void setSort_order(String sort_order) {
        this.sort_order = sort_order;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
