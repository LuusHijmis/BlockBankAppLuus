package com.blockbank.database.domain;

/**
 * @author Fiona Lampers
 * Super klasse User.
 * Subklasse UserDetails.
 * When ClientDetails are filled in including address, the contructor automatically makes roll = client
 * for futher client priveliges/ restrictions.
 */


import java.util.Objects;

public class UserDetails extends User {
    private ClientDetails clientDetails;
    private Address address;

    // Constructor for the Client
    public UserDetails(String username, String password, String salt, ClientDetails clientDetails, Address address) {
        super(username, password, "client", salt);
        this.clientDetails = clientDetails;
        this.address = address;
    }

    // Constructor for the Administrator
    public UserDetails(String username, String password, String salt) {
        super(username, password, "administrator", salt);
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "UserDetails{" + super.toString() +
                "clientDetails=" + clientDetails +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(clientDetails, that.clientDetails) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientDetails, address);
    }
}
