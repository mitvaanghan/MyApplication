package com.example.myapplication.model;

public class LabourClass {
    private String name,mnumber,aadhar , address;

    public LabourClass(){}

    public LabourClass(String name, String mnumber, String aadhar , String address) {
        this.name = name;
        this.mnumber = mnumber;
        this.aadhar = aadhar;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMnumber() {
        return mnumber;
    }

    public void setMnumber(String mnumber) {
        this.mnumber = mnumber;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
}
