package com.example.medicinesapplication;

public class Medicine {
    private String name;
    private String type;
    private String picture;
    private String purpose;
    private String quantity;
    private String available;
    private String manufacturer;
    private String allergen1;
    private String allergen2;
    private String amount1;
    private String amount2;
    private String price;

    public Medicine(){}

    public Medicine(String name, String type, String picture, String purpose, String quantity, String available, String manufacturer, String allergen1, String allergen2, String amount1, String amount2, String price) {
        this.name = name;
        this.type = type;
        this.picture = picture;
        this.purpose = purpose;
        this.quantity = quantity;
        this.available = available;
        this.manufacturer = manufacturer;
        this.allergen1 = allergen1;
        this.allergen2 = allergen2;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAllergen1() {
        return allergen1;
    }

    public void setAllergen1(String allergen1) {
        this.allergen1 = allergen1;
    }

    public String getAllergen2() {
        return allergen2;
    }

    public void setAllergen2(String allergen2) {
        this.allergen2 = allergen2;
    }

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
