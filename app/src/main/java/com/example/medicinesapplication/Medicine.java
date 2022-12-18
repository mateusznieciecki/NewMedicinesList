package com.example.medicinesapplication;

public class Medicine {
    private String name;
    private String type;
    private String picture;
    private String purpose;

    public Medicine(){}
    public Medicine(String name, String type, String picture, String purpose) {
        this.name = name;
        this.type = type;
        this.picture = picture;
        this.purpose = purpose;
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
}
