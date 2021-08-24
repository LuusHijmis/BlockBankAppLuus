package com.blockbank.database.domain;

import java.util.Date;

public class PersonalData {

    private String firstname;
    private String prefix;
    private String lastname;
    private Date dateOfBirth;
    private int bsn;
    private String emailAddress;

    public PersonalData(String firstname, String prefix, String lastname, Date dateOfBirth, int bsn, String emailAddress) {
        this.firstname = firstname;
        this.prefix = prefix;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.bsn = bsn;
        this.emailAddress = emailAddress;
    }


}
