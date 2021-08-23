package com.blockbank.database.domain;

/*@author Fiona Lampers
 * Super class  */

import java.util.Date;

public class Client extends User {
    private String firstname;
    private String prefix;
    private String lastname;
    private String address;
    private int houseNumber;
    private String affix; // addition/ extension to houseNumber example a, b or Red.
    private String zipcode;
    private String city;
    private Date dateOfBirth;
    private int bsn;
    private int clientID;
    private String emailAddress;

    public Client(String username, String password, String firstname, String prefix, String lastname,
                  String address, int houseNumber, String affix, String zipcode, String city, Date dateOfBirth,
                  int bsn, int clientID, String emailAddress) {
        super(username, password, "client");
        this.firstname = firstname;
        this.prefix = prefix;
        this.lastname = lastname;
        this.address = address;
        this.houseNumber = houseNumber;
        this.affix = affix;
        this.zipcode = zipcode;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.bsn = bsn;
        this.clientID = clientID;
        this.emailAddress = emailAddress;
    }

}
