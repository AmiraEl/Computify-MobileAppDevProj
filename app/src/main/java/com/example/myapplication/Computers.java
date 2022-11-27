package com.example.myapplication;

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

    @Override
    public String toString(){
        return "Computers{" +
                ", cpu='" + cpu + '\'' +
                ", gpu= '" + gpu + '\''+
                ", ram='" + ram + '\'' +
                ", pcase= '" + pcase + '\''+
                ", motherboard='" + motherboard + '\'' +
                ", powersupply= '" + powersupply + '\''+
                ", hdd='" + hdd + '\'' +
                ", ssd= '" + ssd + '\'' +
                ", price= " + price +
                '}';

    }

    public Computers(){}

    public Computers(String cpu, String gpu, String ram, String pcase,
                     String motherboard, String powersupply, String hdd,
                     String ssd, String price )
    {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram= ram;
        this.pcase = pcase;
        this.motherboard = motherboard;
        this.powersupply = powersupply;
        this.hdd = hdd;
        this.ssd = ssd;
        this.price=price;
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

    public String getPrice(){
        return price;
    }
}
