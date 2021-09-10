package com.blockbank.database.domain;

public class PortfolioDTO {

    private String assetID;
    private String assetName;
    private String assetSymbol;
    private double exchangeRate;
    private double assetAmount;

    public PortfolioDTO(String assetID, String assetName, String assetSymbol, double exchangeRate, double assetAmount) {
        this.assetID = assetID;
        this.assetName = assetName;
        this.assetSymbol = assetSymbol;
        this.exchangeRate = exchangeRate;
        this.assetAmount = assetAmount;
    }

    @Override
    public String toString() {
        return
                assetID + " " +
                assetName + " " +
                assetSymbol + " " +
                exchangeRate + " " +
                assetAmount;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(double assetAmount) {
        this.assetAmount = assetAmount;
    }
}
