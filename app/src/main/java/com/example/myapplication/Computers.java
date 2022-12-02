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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computers computers = (Computers) o;
        return (Objects.equals(cpu, computers.cpu)|| computers.cpu.isEmpty())
                && (Objects.equals(gpu, computers.gpu)|| computers.gpu.isEmpty())
                && (Objects.equals(ram, computers.ram)|| computers.ram.isEmpty())
                && (Objects.equals(pcase, computers.pcase)|| computers.pcase.isEmpty())
                && (Objects.equals(motherboard, computers.motherboard) || computers.motherboard.isEmpty())
                && (Objects.equals(powersupply, computers.powersupply)|| computers.powersupply.isEmpty())
                && (Objects.equals(hdd, computers.hdd)|| computers.hdd.isEmpty())
                && (Objects.equals(ssd, computers.ssd)|| computers.ssd.isEmpty())
                && (Objects.equals(name.toLowerCase(), computers.name.toLowerCase()) || computers.name.isEmpty());
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

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setPcase(String pcase) {
        this.pcase = pcase;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public void setPowersupply(String powersupply) {
        this.powersupply = powersupply;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSellerID() {
        return SellerID;
    }

    public void setSellerID(String sellerID) {
        SellerID = sellerID;
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

    public String getPcID(){
        return pcID;
    }

    public void setPcID(String pcID){
        this.pcID = pcID;
    }
}
