package com.blockbank.service;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class HashHelper {

    public static final int ITERATIONS = 1000;
    public static final int KEYLENGTH = 64;
    public static final int KEYLENGTH_FACTOR= 8;

    private static final String SHA_512 = "SHA-512";
    private static final String PBKDF2 = "PBKDF2WithHmacSHA1";
    public static final String ALGORITME_BESTAAT_NIET = "Algoritme bestaat niet";
    public static final String PEPPER = "FioKerHanAleHarLuuBLBK2021";

    public static String generateStrongPasswordHash (String password, String salt){
        try {
            char[] chars = password.toCharArray();
            byte[] saltToByte = salt.getBytes(StandardCharsets.UTF_8);
            PBEKeySpec spec = new PBEKeySpec(chars, saltToByte, ITERATIONS, KEYLENGTH * KEYLENGTH_FACTOR);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return ByteArrayToHexHelper.encodeHexString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        } throw new RuntimeException(ALGORITME_BESTAAT_NIET);
    }

    public static String hash(String password, String salt) {
        try {
            MessageDigest sha = MessageDigest.getInstance(SHA_512);
            sha.update(password.getBytes(StandardCharsets.UTF_8));
            sha.update(salt.getBytes(StandardCharsets.UTF_8));
            sha.update(PEPPER.getBytes(StandardCharsets.UTF_8));
            byte[] digest = sha.digest();
            return ByteArrayToHexHelper.encodeHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(ALGORITME_BESTAAT_NIET);
        }
    }
    public static String hash(String value) {
        try {
            MessageDigest sha = MessageDigest.getInstance(SHA_512);
            sha.update(value.getBytes(StandardCharsets.UTF_8));
            byte[] digest = sha.digest();
            return ByteArrayToHexHelper.encodeHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("ALGORITME_BESTAAT_NIET");
        }
    }
}
