package com.app.adarsh.offersbymall;


import static com.example.adarsh.offersbymall.R.id.shopName;

public class OfferDetails {
    private String offer="", shopName="", category="";
    private String offer2="",offer3="",offer_cost="",offer2_cost="",offer3_cost="";
    private String offer_image="",offer2_image="",offer3_image;
    private int shopImage;


    public int getShopImage() {
        return shopImage;
    }

    public void setShopImage(int shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getOffer3_image() {
        return offer3_image;
    }

    public void setOffer3_image(String offer3_image) {
        this.offer3_image = offer3_image;
    }

    public String getOffer2_image() {
        return offer2_image;
    }

    public void setOffer2_image(String offer2_image) {
        this.offer2_image = offer2_image;
    }

    public String getOffer_image() {
        return offer_image;
    }

    public void setOffer_image(String offer_image) {
        this.offer_image = offer_image;
    }

    public String getOffer3_cost() {
        return offer3_cost;
    }

    public void setOffer3_cost(String offer3_cost) {
        this.offer3_cost = offer3_cost;
    }

    public String getOffer2_cost() {
        return offer2_cost;
    }

    public void setOffer2_cost(String offer2_cost) {
        this.offer2_cost = offer2_cost;
    }

    public String getOffer_cost() {
        return offer_cost;
    }

    public void setOffer_cost(String offer_cost) {
        this.offer_cost = offer_cost;
    }

    public String getOffer3() {
        return offer3;
    }

    public void setOffer3(String offer3) {
        this.offer3 = offer3;
    }

    public String getOffer2() {
        return offer2;
    }

    public void setOffer2(String offer2) {
        this.offer2 = offer2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
