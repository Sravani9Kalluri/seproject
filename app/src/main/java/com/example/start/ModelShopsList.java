package com.example.start;

public class ModelShopsList {
    private String shop;
    private String category;

    public ModelShopsList(String shop, String category) {
        this.shop = shop;
        this.category = category;
    }

    public String getShop() {
        return this.shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
