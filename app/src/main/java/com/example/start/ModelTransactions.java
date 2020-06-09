package com.example.start;

public class ModelTransactions {
    private String amount;
    private String shop;
    private String category;
    private String date;
    private String id;

    public ModelTransactions(double amount,String shop,String category,String date,String id) {
        this.amount = Double.toString(amount);
        this.shop = shop;
        this.category = category;
        this.date = date;
        this.id=id;
    }

    public void setAmount(double num) {
        this.amount = Double.toString(num);
    }

    public String getAmount() {
        return this.amount;
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

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
