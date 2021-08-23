package com.blockbank.database.domain;

/*@author Fiona Lampers
 * Super class  */

public abstract class User {

    private int clientID = 0;
    private String username;
    private String role;
    private String password;
    private String salt;

    public User(String username, String role, String password, String salt) {
        this(username, password, role);
        this.salt = salt; // maken wij zelf (automatisch)
    }

    public User(String username, String role, String password) {
        this(username, password);
        this.role = role;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        clientID = 0;
    }


    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
