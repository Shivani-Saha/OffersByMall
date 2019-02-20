package com.app.adarsh.offersbymall;

public class UserDetails {
    private String name, email, password, phone, shopNo, offer, city, mall, category, uri;


    public UserDetails() {
    }


    public UserDetails(String name, String email, String password, String phone, String shopNo, String offer, String city, String mall, String category, String uri) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.shopNo = shopNo;
        this.offer = offer;
        this.city = city;
        this.mall = mall;
        this.category = category;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
