package com.daimler.msrp.price;

public class RetailPriceModel {

    private String vin;
    private String newPrice;

    public RetailPriceModel(String vin, String newPrice) {
        this.vin = vin;
        this.newPrice = newPrice;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public String toString() {
        return "RetailPriceModel{" +
                "vin='" + vin + '\'' +
                ", newPrice='" + newPrice + '\'' +
                '}';
    }
}
