package com.blockbank.database.domain;

public class AssetDTO {

    private String symbol;
    private String name;
    private double exchangeRate;


    public AssetDTO(String symbol, String name, double exchangeRate) {
        this.symbol = symbol;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "AssetDTO{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
