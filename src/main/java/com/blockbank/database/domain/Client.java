package com.blockbank.database.domain;

/*@author Fiona Lampers
 * Super class  */

import java.util.Date;

public class Client extends User {
    private PersonalData personalData;
    private Address address;

    public Client(String username, String password, PersonalData personalData, Address address) {
        super(username, password, "client");
        this.personalData = personalData;
        this.address = address;
        this.getUserID();
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
