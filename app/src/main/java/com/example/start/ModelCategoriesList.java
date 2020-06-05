package com.example.start;

public class ModelCategoriesList {
    private String category;
    private String limit;
    private String amount;

    public ModelCategoriesList(String category, double limit, double amount) {
        this.category = category;
        this.limit = Double.toString(limit);
        this.amount = Double.toString(amount);
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLimit() {
        return this.limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setAmount(double num) {
        this.amount = Double.toString(num);
    }

    public String getAmount() {
        return this.amount;
    }

}
