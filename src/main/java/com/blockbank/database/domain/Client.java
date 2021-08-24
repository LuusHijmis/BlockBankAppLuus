package com.blockbank.database.domain;

/*@author Fiona Lampers
 * Super class  */

import java.util.Date;

public class Client extends User {
    private PersonalData personalData;
    private int clientID;
    private Address address;

    public Client(int clientID, String username, String password, PersonalData personalData, Address address) {
        super(username, password, "client");
        this.clientID = clientID;
        this.personalData = personalData;
        this.address = address;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    @Override
    public int getClientID() {
        return clientID;
    }

    @Override
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
