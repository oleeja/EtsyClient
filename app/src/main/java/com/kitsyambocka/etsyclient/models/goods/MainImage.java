package com.kitsyambocka.etsyclient.models.goods;

/**
 * Created by Developer on 10.12.2016.
 */

public class MainImage {
    private long listing_image_id;

    private long listing_id;

    private String url_75x75;

    private String url_170x135;

    private String url_570xN;

    private String url_fullxfull;

    public long getListing_image_id() {
        return listing_image_id;
    }

    public void setListing_image_id(long listing_image_id) {
        this.listing_image_id = listing_image_id;
    }

    public long getListing_id() {
        return listing_id;
    }

    public void setListing_id(long listing_id) {
        this.listing_id = listing_id;
    }

    public String getUrl_75x75() {
        return url_75x75;
    }

    public void setUrl_75x75(String url_75x75) {
        this.url_75x75 = url_75x75;
    }

    public String getUrl_170x135() {
        return url_170x135;
    }

    public void setUrl_170x135(String url_170x135) {
        this.url_170x135 = url_170x135;
    }

    public String getUrl_570xN() {
        return url_570xN;
    }

    public void setUrl_570xN(String url_570xN) {
        this.url_570xN = url_570xN;
    }

    public String getUrl_fullxfull() {
        return url_fullxfull;
    }

    public void setUrl_fullxfull(String url_fullxfull) {
        this.url_fullxfull = url_fullxfull;
    }
}
