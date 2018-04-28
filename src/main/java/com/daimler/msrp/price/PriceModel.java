package com.daimler.msrp.price;

public class PriceModel {

    private String id;
    private String originalPrice;
    private String changePrice;

    public PriceModel(String id, String originalPrice, String changePrice) {
        this.id = id;
        this.originalPrice = originalPrice;
        this.changePrice = changePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(String changePrice) {
        this.changePrice = changePrice;
    }

    @Override
    public String toString() {
        return "PriceModel{" +
                "id='" + id + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", changePrice='" + changePrice + '\'' +
                '}';
    }
}
