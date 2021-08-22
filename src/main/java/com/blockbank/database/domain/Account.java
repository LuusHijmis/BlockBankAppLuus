package com.blockbank.database.domain;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Account {

    private final Logger logger = LoggerFactory.getLogger(Account.class);
    private int iban;
    private double balance;
    private  int clientId;

    public Account(double balance, int clientId) {
        super();
        // iban must be generated via a service class.
        this.iban = 0;
        this.balance = balance;
        this.clientId = clientId;
        logger.info("New Account for client " + clientId);
    }

    public int getIban() {return iban;}

    public double getBalance() {return balance;}

    public int getClientId() {return  clientId;}

    public void setIban(int iban) {this.iban = iban;}

    public void setBalance(double balance) {this.balance = balance;}

    @Override
    public String toString() {
        return String.format("Account with iban %s belongs to client with id %d. Balamce: %f.",
                iban, clientId, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return iban == account.iban && Double.compare(account.balance, balance) == 0 && clientId == account.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, balance, clientId);
    }
}