package com.blockbank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class SaltGenerator {

    private static final int SALT_LENGTH = 32;
    private int saltLength;
    private SecureRandom secureRNG;

    private final Logger logger = LoggerFactory.getLogger(SaltGenerator.class);

    public SaltGenerator(int saltLength) {
        this.saltLength = saltLength;
        secureRNG = new SecureRandom();
        logger.info("New SaltGenerator");
    }
    public SaltGenerator() {
        this(SALT_LENGTH);
    }

    public String generateSalt() {
        int tempLengte = saltLength / 2;
        byte[] arr = new byte[saltLength % 2 == 0 ? tempLengte : tempLengte + 1]; // 1 byte geeft 2 karakters, bij oneven lengte geet integer deling onderwaarde
        secureRNG.nextBytes(arr);
        String salt = ByteArrayToHexHelper.encodeHexString(arr);
        return saltLength % 2 == 0 ? salt : salt.substring(1); // als oneven is er 1 karakter teveel, haal deze weg
    }

    public void setlength(int saltLength) {
        this.saltLength = SALT_LENGTH;
    }
}
