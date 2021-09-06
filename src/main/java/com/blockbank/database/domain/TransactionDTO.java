package com.blockbank.database.domain;

import java.time.LocalDateTime;

public class TransactionDTO {

    private String username;
    private int assetID;
    private String assetName;
    private double assetAmount;
    private double exchangeRate;
    private String getTransactionDescription;
    private LocalDateTime localDateTime;
    private double transactionRate;


    public TransactionDTO(String username, int assetID, String assetName, double assetAmount, double exchangeRate,
                          String getTransactionDescription, LocalDateTime localDateTime, double transactionRate) {
        this.username = username;
        this.assetID = assetID;
        this.assetName = assetName;
        this.assetAmount = assetAmount;
        this.exchangeRate = exchangeRate;
        this.getTransactionDescription = getTransactionDescription;
        this.localDateTime = localDateTime;
        this.transactionRate = transactionRate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public double getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(double assetAmount) {
        this.assetAmount = assetAmount;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getGetTransactionDescription() {
        return getTransactionDescription;
    }

    public void setGetTransactionDescription(String getTransactionDescription) {
        this.getTransactionDescription = getTransactionDescription;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public double getTransactionRate() {
        return transactionRate;
    }

    public void setTransactionRate(double transactionRate) {
        this.transactionRate = transactionRate;
    }
}
