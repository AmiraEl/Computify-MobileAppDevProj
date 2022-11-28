package com.example.myapplication;

import java.util.Objects;

public class Computers {
    private String cpu;
    private String gpu;
    private String ram;
    private String pcase;
    private String motherboard;
    private String powersupply;
    private String hdd;
    private String ssd;
    private String price;
    private String name;
    private int ID = 0;
    private String SellerID;
    public int Max_ID;

    public Computers(String cpu, String gpu, String ram, String pcase, String motherboard, String powersupply, String hdd, String ssd, String price, String name, String sellerID) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.pcase = pcase;
        this.motherboard = motherboard;
        this.powersupply = powersupply;
        this.hdd = hdd;
        this.ssd = ssd;
        this.price = price;
        this.name = name;
        this.ID = Max_ID + 1;
        Max_ID = this.ID;
        SellerID = sellerID;
    }

    @Override
    public String toString() {
        return "Computers{" +
                "cpu='" + cpu + '\'' +
                ", gpu='" + gpu + '\'' +
                ", ram='" + ram + '\'' +
                ", pcase='" + pcase + '\'' +
                ", motherboard='" + motherboard + '\'' +
                ", powersupply='" + powersupply + '\'' +
                ", hdd='" + hdd + '\'' +
                ", ssd='" + ssd + '\'' +
                ", price='" + price + '\'' +
                ", name='" + name + '\'' +
                ", ID=" + ID +
                ", SellerID='" + SellerID + '\'' +
                ", Max_ID=" + Max_ID +
                '}';
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSellerID() {
        return SellerID;
    }

    public void setSellerID(String sellerID) {
        SellerID = sellerID;
    }

    public int getMax_ID() {
        return Max_ID;
    }

    public void setMax_ID(int max_ID) {
        Max_ID = max_ID;
    }

    public Computers() {
    }

    public String getCpu() {
        return cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public String getRam() {
        return ram;
    }

    public String getPcase() {
        return pcase;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public String getPowersupply() {
        return powersupply;
    }

    public String getHdd() {
        return hdd;
    }

    public String getSsd() {
        return ssd;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
