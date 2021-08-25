package com.blockbank.database.domain;

/**
 * @author hannahvd
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Portfolio {

    private final Logger logger = LoggerFactory.getLogger(Portfolio.class);

    private int clientId; //should be linked to Client model?
    private Map<Asset, Double> assetList;

    public Portfolio(int clientId) {
        super();
        this.clientId = clientId;
        assetList = new HashMap<>();
        logger.info("New portfolio created for client " + clientId);
    }

    public void addToAssetList(Asset asset, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be 1 or more");
        }
        this.assetList.put(asset, amount);
    }

    public int getClientId() {
        return clientId;
    }

    public Map<Asset, Double> getAssetList() {
        return assetList;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "clientId=" + clientId +
                ", assetList=" + assetList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return clientId == portfolio.clientId && assetList.equals(portfolio.assetList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, assetList);
    }
}
