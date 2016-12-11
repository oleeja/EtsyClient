package com.kitsyambocka.etsyclient.models.goods;

import java.util.List;

/**
 * Created by Developer on 10.12.2016.
 */

public class ResultGoods {
    private long listing_id;

    private String state;

    private long userId;

    private long category_id;

    private String title;

    private String description;

    private String price;

    private String currency_code;

    private List<String> tags = null;

    private List<String> category_path = null;

    private List<Long> category_path_ids = null;

    private long taxonomy_id;

    private List<String> taxonomy_path = null;

    private boolean used_manufacturer;

    private MainImage MainImage = new MainImage();

    public long getListingId() {
        return listing_id;
    }

    public void setListingId(long listingId) {
        this.listing_id = listingId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCategoryId() {
        return category_id;
    }

    public void setCategoryId(long categoryId) {
        this.category_id = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currency_code;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currency_code = currencyCode;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getCategoryPath() {
        return category_path;
    }

    public void setCategoryPath(List<String> categoryPath) {
        this.category_path = categoryPath;
    }

    public List<Long> getCategoryPathIds() {
        return category_path_ids;
    }

    public void setCategoryPathIds(List<Long> categoryPathIds) {
        this.category_path_ids = categoryPathIds;
    }


    public long getTaxonomyId() {
        return taxonomy_id;
    }

    public void setTaxonomyId(long taxonomyId) {
        this.taxonomy_id = taxonomyId;
    }

    public List<String> getTaxonomyPath() {
        return taxonomy_path;
    }

    public void setTaxonomyPath(List<String> taxonomyPath) {
        this.taxonomy_path = taxonomyPath;
    }

    public boolean isUsedManufacturer() {
        return used_manufacturer;
    }

    public void setUsedManufacturer(boolean usedManufacturer) {
        this.used_manufacturer = usedManufacturer;
    }

    public MainImage getMainImage() {
        return MainImage;
    }

    public void setMainImage(MainImage MainImage) {
        this.MainImage = MainImage;
    }
}
