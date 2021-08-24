package com.blockbank.database.domain;

public class Address {
    private String address;
    private int houseNumber;
    private String affix; // addition/ extension to houseNumber example a, b or Red.
    private String postalCode;
    private String city;
    private String country;

    public Address(String address, int houseNumber, String affix, String postalCode, String city, String country) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.affix = affix;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

}
