package com.blockbank.database.domain;

import java.util.Objects;

public class Asset {

    private String assetID;
    private String name;
    private String symbol;
    private String description;
    private double exchangeRate;

   public Asset(String assetID, String name, String symbol, String description, double exchangeRate) {
        this.assetID = assetID;
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.exchangeRate = exchangeRate;
    }

    public Asset() {

    }

    public Asset(String assetID) {
        this.assetID = assetID;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return Double.compare(asset.exchangeRate, exchangeRate) == 0 && Objects.equals(assetID, asset.assetID) && Objects.equals(name, asset.name) && Objects.equals(symbol, asset.symbol) && Objects.equals(description, asset.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetID, name, symbol, description, exchangeRate);
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetID='" + assetID + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                ", exchangeRate=" + exchangeRate + '\n'+
                '}';
    }
}
