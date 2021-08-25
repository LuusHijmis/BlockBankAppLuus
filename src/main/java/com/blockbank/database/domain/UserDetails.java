package com.blockbank.database.domain;

/*@author Fiona Lampers
 * Super class  */

public class UserDetails extends User {
    private ClientDetails clientDetails;
    private Address address;

    public UserDetails(String username, String password, ClientDetails clientDetails, Address address) {
        super(username, password);
        this.clientDetails = clientDetails;
        this.address = address;
//        this.getUserID();
    }

    // Constructor for the Bank
    public UserDetails(String username, String password, Address address) {
          this(username, password, null, address);
//        this.getUserID();
    }

    // Constructor for the Administrator
    public UserDetails(String username, String password) {
        this(username, password, null, null);
        this.getUserID();
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
}
