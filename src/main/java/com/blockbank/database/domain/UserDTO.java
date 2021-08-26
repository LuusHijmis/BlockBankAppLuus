package com.blockbank.database.domain;
/*@author Karish Resodikromo
* Data transfer object van user
* */


import org.springframework.stereotype.Component;

@Component
public class UserDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
