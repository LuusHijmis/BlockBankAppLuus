package com.blockbank.database.domain;

public class Asset {

    private int assetID;
    private String name;
    private String symbol;
    private String description;
    private double exchangeRate;

    public Asset(int assetID, String name, String symbol, String description, double exchangeRate) {
        this.assetID = assetID;
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.exchangeRate = exchangeRate;
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
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
}
