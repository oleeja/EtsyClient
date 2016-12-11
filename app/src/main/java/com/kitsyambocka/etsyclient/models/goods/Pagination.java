package com.kitsyambocka.etsyclient.models.goods;

/**
 * Created by Developer on 10.12.2016.
 */

public class Pagination {

    private long effective_limit;

    private long effective_offset;

    private long next_offset;

    private long effective_page;

    private long next_page;

    public long getEffective_limit() {
        return effective_limit;
    }

    public void setEffective_limit(long effective_limit) {
        this.effective_limit = effective_limit;
    }

    public long getEffective_offset() {
        return effective_offset;
    }

    public void setEffective_offset(long effective_offset) {
        this.effective_offset = effective_offset;
    }

    public long getNext_offset() {
        return next_offset;
    }

    public void setNext_offset(long next_offset) {
        this.next_offset = next_offset;
    }

    public long getEffective_page() {
        return effective_page;
    }

    public void setEffective_page(long effective_page) {
        this.effective_page = effective_page;
    }

    public long getNext_page() {
        return next_page;
    }

    public void setNext_page(long next_page) {
        this.next_page = next_page;
    }
}
