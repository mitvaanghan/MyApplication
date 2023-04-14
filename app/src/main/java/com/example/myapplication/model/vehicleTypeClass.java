package com.example.myapplication.model;

public class vehicleTypeClass {

    private  String type , pnumber , drivercnumber , drivername;

    public vehicleTypeClass(){}

    public vehicleTypeClass(String type, String pnumber, String drivercnumber, String drivername) {
        this.type = type;
        this.pnumber = pnumber;
        this.drivercnumber = drivercnumber;
        this.drivername = drivername;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getDrivercnumber() {
        return drivercnumber;
    }

    public void setDrivercnumber(String drivercnumber) {
        this.drivercnumber = drivercnumber;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }
}
