package com.app.adarsh.offersbymall;


public class VendorInfo {
    String shop_category;
    String mall_name;
    String shop_name;
    String shop_no;
    String image;
    String email;
    String owner;
    String stat;
    String contact;
    String id;

    public VendorInfo(){
        id="";
        shop_category="";
        mall_name="";
        shop_name="";
        shop_no="";
        image="";
        email="";
        owner="";
        id="";
        stat="";
        contact="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getShop_category() {
        return shop_category;
    }

    public void setShop_category(String shop_category) {
        this.shop_category = shop_category;
    }

    public String getMall_name() {
        return mall_name;
    }

    public void setMall_name(String mall_id) {
        this.mall_name = mall_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getShop_no() {
        return shop_no;
    }

    public void setShop_no(String shop_no) {
        this.shop_no = shop_no;
    }
}
