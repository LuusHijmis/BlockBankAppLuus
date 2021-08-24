package com.blockbank.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashService {

    public static final String ALGORITME_BESTAAT_NIET = "Algoritme bestaat niet";

    public static String hash(String password, String salt) {
        try {
            MessageDigest sha = MessageDigest.getInstance("MD5");
            sha.update(password.getBytes(StandardCharsets.UTF_8));
            sha.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] digest = sha.digest();
            return ByteArrayToHexHelper.encodeHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(ALGORITME_BESTAAT_NIET);
        }
    }
}
