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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAffix() {
        return affix;
    }

    public void setAffix(String affix) {
        this.affix = affix;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
