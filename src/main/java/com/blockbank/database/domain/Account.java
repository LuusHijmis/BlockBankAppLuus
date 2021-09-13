package com.blockbank.database.domain;

/**
 * @author Alex Shijan
 * Model voor het aanmaken van accounts voor klanten.
 * Heeft eeen iban, balance en clientId.
 * iban is een string omdat die bestaat uit alphanumerieke tekens.
 * iban wordt gegenereert in de bijbehorende service klasse.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Account {

    private final Logger logger = LoggerFactory.getLogger(Account.class);
    private String iban;
    private double balance;
    private  int clientId;
    private final double STARTING_BALANCE = 100;
    private final String EMPTY_IBAN = "";

    public Account(int clientId) throws IllegalArgumentException {
        super();
        if (clientId < 0) {
            throw new IllegalArgumentException();
        }
        // iban must be generated via a service class.
        this.iban = EMPTY_IBAN;
        this.balance = STARTING_BALANCE;
        this.clientId = clientId;
        logger.info("New Account for client " + clientId);
    }

    public String getIban() {return iban;}

    public double getBalance() {return balance;}

    public int getClientId() {return  clientId;}

    public void setIban(String iban) {this.iban = iban;}

    // balance cant be set to negative.
    public void setBalance(double balance) throws IllegalArgumentException {
        if(balance < 0) {
            throw new IllegalArgumentException();
        }
        this.balance = balance;}

    @Override
    public String toString() {
        return String.format("Account with iban %s belongs to client with id %d. Balance: %.2f.",
                iban, clientId, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && clientId == account.clientId && iban.equals(account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, balance, clientId);
    }
}
