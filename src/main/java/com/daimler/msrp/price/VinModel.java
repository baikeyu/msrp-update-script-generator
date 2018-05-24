package com.daimler.msrp.price;

public class VinModel {

    private String vin;
    private String dealerCode;

    public VinModel(String vin, String dealerCode) {
        this.vin = vin;
        this.dealerCode = dealerCode;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    @Override
    public String toString() {
        return "VinModel{" +
                "vin='" + vin + '\'' +
                ", dealerCode='" + dealerCode + '\'' +
                '}';
    }
}
