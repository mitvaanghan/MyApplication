package com.example.myapplication.model;

public class vehicleDetailsClass {

    private String Charge;
    private String imgUrl;
    private String Type;
    public vehicleDetailsClass(){}

    public vehicleDetailsClass(String charge, String imgUrl,String Type) {
        this.Charge = charge;
        this.imgUrl = imgUrl;
        this.Type=Type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCharge() {
        return Charge;
    }

    public void setCharge(String charge) {
        Charge = charge;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}