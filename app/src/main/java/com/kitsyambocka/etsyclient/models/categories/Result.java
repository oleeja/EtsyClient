package com.kitsyambocka.etsyclient.models.categories;

/**
 * Created by Developer on 09.12.2016.
 */

public class Result {

    private long category_id;

    private String name;

    private String meta_title;

    private String meta_keywords;

    private String meta_description;

    private String page_description;

    private String page_title;

    private String category_name;

    private String short_name;

    private String long_name;

    private long num_children;

    public long getCategoryId() {
        return category_id;
    }

    public void setCategoryId(long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetaTitle() {
        return meta_title;
    }

    public void setMetaTitle(String meta_title) {
        this.meta_title = meta_title;
    }

    public String getMetaKeywords() {
        return meta_keywords;
    }

    public void setMetaKeywords(String meta_keywords) {
        this.meta_keywords = meta_keywords;
    }

    public String getMetaDescription() {
        return meta_description;
    }

    public void setMetaDescription(String meta_description) {
        this.meta_description = meta_description;
    }

    public String getPageDescription() {
        return page_description;
    }

    public void setPageDescription(String page_description) {
        this.page_description = page_description;
    }

    public String getPageTitle() {
        return page_title;
    }

    public void setPageTitle(String page_title) {
        this.page_title = page_title;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
    }

    public String getShortName() {
        return short_name;
    }

    public void setShortName(String short_name) {
        this.short_name = short_name;
    }

    public String getLongName() {
        return long_name;
    }

    public void setLongName(String long_name) {
        this.long_name = long_name;
    }

    public long getNumChildren() {
        return num_children;
    }

    public void setNumChildren(long num_children) {
        this.num_children = num_children;
    }
}
