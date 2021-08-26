package com.blockbank.database.domain;

/*@author Fiona Lampers
 * Super class  */

public abstract class User {

    private int userID = 0;
    private String username;
    private String role;
    private String password;
    private String salt;

    public User(String username, String password, String role, String salt) {
        this(username, password, salt);
        this.role = role;
        userID = 0;
    }

    public User(String username, String password, String salt) {
        this(username, password);
        this.salt = salt; // maken wij zelf (automatisch)
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", role='" + role + '\'' +
                '}';
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
