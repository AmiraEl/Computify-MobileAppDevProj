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
    private String SellerID;
    private String pcID;

    public Computers(String cpu, String gpu, String ram, String pcase, String motherboard, String powersupply, String hdd, String ssd, String price, String name, String sellerID, String pcID) {
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
        this.SellerID = sellerID;
        this.pcID = pcID;

    }

    public Computers() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Computers computers = (Computers) o;
//        Objects.equals(cpu.toLowerCase(), computers.cpu.toLowerCase()
        return (computers.cpu.toLowerCase().contains(cpu.toLowerCase()) || cpu.isEmpty())
                && (computers.gpu.toLowerCase().contains(gpu.toLowerCase()) || gpu.isEmpty())
                && (computers.ram.toLowerCase().contains(ram.toLowerCase()) || ram.isEmpty())
                && (computers.pcase.toLowerCase().contains(pcase.toLowerCase()) || pcase.isEmpty())
                && (computers.motherboard.toLowerCase().contains(motherboard.toLowerCase()) || motherboard.isEmpty())
                && (computers.powersupply.toLowerCase().contains(powersupply.toLowerCase()) || powersupply.isEmpty())
                && (computers.hdd.toLowerCase().contains(hdd.toLowerCase()) || hdd.isEmpty())
                && (computers.ssd.toLowerCase().contains(ssd.toLowerCase()) || ssd.isEmpty())
                && (computers.name.toLowerCase().contains(name.toLowerCase()) || name.isEmpty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpu, gpu, ram, pcase, motherboard, powersupply, hdd, ssd, price, name);
    }

    @Override
    public String toString() {
        return "Computers{" +
                "cpu '" + cpu + '\'' +
                ", gpu '" + gpu + '\'' +
                ", ram '" + ram + '\'' +
                ", pcase '" + pcase + '\'' +
                ", motherboard '" + motherboard + '\'' +
                ", powersupply '" + powersupply + '\'' +
                ", hdd '" + hdd + '\'' +
                ", ssd '" + ssd + '\'' +
                ", price '" + price + '\'' +
                ", name '" + name + '\'' +
                ", SellerID '" + SellerID + '\'' +
                ", pcid '" + pcID +
                '}';
    }

    public String getSellerID() {
        return SellerID;
    }

    public void setSellerID(String sellerID) {
        SellerID = sellerID;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getPcase() {
        return pcase;
    }

    public void setPcase(String pcase) {
        this.pcase = pcase;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getPowersupply() {
        return powersupply;
    }

    public void setPowersupply(String powersupply) {
        this.powersupply = powersupply;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcID() {
        return pcID;
    }

    public void setPcID(String pcID) {
        this.pcID = pcID;
    }
}
