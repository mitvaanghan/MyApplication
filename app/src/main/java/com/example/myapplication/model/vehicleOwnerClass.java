package com.example.myapplication.model;

public class vehicleOwnerClass {

    private String name,contact ,email ,city;

    public vehicleOwnerClass(){}

    public vehicleOwnerClass(String name, String contact ,String city,String email) {
        this.name = name;
        this.contact = contact;
        this.email = email ;
        this.city = city;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
