package com.blockbank.database.domain;

import java.time.LocalDateTime;

public class TransactionDTO {

//    private final int DEFAULT_OPPOSINGUSERID = 0;
//    private String username;
    private int userID;
    private LocalDateTime transactionDateTime;
    private String transactionSort;
    private double amountAssets;
    private double exchangeRate;
    private double transactionFee;
    private int opposingUserID;
    private int assetID;

    public TransactionDTO(int userID, LocalDateTime transactionDateTime, String transactionSort, double amountAssets,
                          double exchangeRate, double transactionFee, int opposingUserID, int assetID) {
        this.userID = userID;
        this.transactionDateTime = transactionDateTime;
        this.transactionSort = transactionSort;
        this.amountAssets = amountAssets;
        this.exchangeRate = exchangeRate;
        this.transactionFee = transactionFee;
        this.opposingUserID = opposingUserID;
        this.assetID = assetID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getTransactionSort() {
        return transactionSort;
    }

    public void setTransactionSort(String transactionSort) {
        this.transactionSort = transactionSort;
    }

    public double getAmountAssets() {
        return amountAssets;
    }

    public void setAmountAssets(double amountAssets) {
        this.amountAssets = amountAssets;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public int getOpposingUserID() {
        return opposingUserID;
    }

    public void setOpposingUserID(int opposingUserID) {
        this.opposingUserID = opposingUserID;
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }
}
