package com.blockbank.database.domain;

/*@author Fiona Lampers
 * Super class  */

import java.util.Date;

public class Client extends User {
    private PersonalData personalData;
    private int clientID;
    private Address address;

    public Client(String username, String password, PersonalData personalData, Address address, int clientID) {
        super(username, password, "client");
        this.address = address;
        this.personalData = personalData;
        this.clientID = clientID;
    }

}
