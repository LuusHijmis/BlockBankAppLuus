package com.blockbank.database.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    private final static String BUY_TRANSACTION_DESCRIPTION= "Buy";
    private final static String SELL_TRANSACTION_DESCRIPTION= "Sell";

    private int transactionID;
    private UserDetails userDetails;
    private UserDetails opposingUserDetails;
    private Asset asset;
    private LocalDateTime localDateTime;
    private String transactionDescription;
    private double assetAmount;
    private double exchangeRate;
    private double transactionRate;

    public Transaction(UserDetails userDetails, UserDetails opposingUserDetails, Asset asset, LocalDateTime localDateTime, String transactionDescription, double assetAmount, double exchangeRate, double transactionRate) {
        this.userDetails = userDetails;
        this.opposingUserDetails = opposingUserDetails;
        this.asset = asset;
        this.localDateTime = localDateTime;
        this.transactionDescription = transactionDescription;
        this.assetAmount = assetAmount;
        this.exchangeRate = exchangeRate;
        this.transactionRate = transactionRate;
        transactionID = 0;
    }

    @Override
    public String toString() {

        return "Transaction{" +
                "opposingUserID=" + opposingUserDetails +
                ", asset=" + asset +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", assetAmount=" + assetAmount +
                '}';
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public UserDetails getOpposingUserDetails() {
        return opposingUserDetails;
    }

    public void setOpposingUserDetails(UserDetails opposingUserDetails) {
        this.opposingUserDetails = opposingUserDetails;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        if(!transactionDescription.equals(BUY_TRANSACTION_DESCRIPTION) || !transactionDescription.equals(SELL_TRANSACTION_DESCRIPTION)) {
            System.out.println("Omschrijving moet 'Buy' of 'Sell' zijn");
        } else {
            this.transactionDescription = transactionDescription;
        }
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

    public double getTransactionRate() {
        return transactionRate;
    }

    public void setTransactionRate(double transactionRate) {
        this.transactionRate = transactionRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionID == that.transactionID && Double.compare(that.assetAmount, assetAmount) == 0 && Double.compare(that.exchangeRate, exchangeRate) == 0 && Double.compare(that.transactionRate, transactionRate) == 0 && userDetails.equals(that.userDetails) && opposingUserDetails.equals(that.opposingUserDetails) && asset.equals(that.asset) && localDateTime.equals(that.localDateTime) && transactionDescription.equals(that.transactionDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionID, userDetails, opposingUserDetails, asset, localDateTime, transactionDescription, assetAmount, exchangeRate, transactionRate);
    }
}
