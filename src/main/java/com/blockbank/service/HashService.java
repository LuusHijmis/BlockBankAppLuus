package com.blockbank.service;

import com.blockbank.database.repository.JdbcAccountDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class HashService {

    private static final int DEFAULT_ROUNDS = 4;
    private final int rounds;
    private final Logger logger = LoggerFactory.getLogger(JdbcAccountDao.class);

    @Autowired
    public HashService() {
        this( DEFAULT_ROUNDS);
    }


    public HashService(int rounds) {
        this.rounds = rounds;
        logger.info("New HashService aangemaakt");
    }

    // eventueel controleren op te grote waarden (< 6)

    public String ultimateHash(String password, String salt) {
        return HashHelper.generateStrongPasswordHash(password,salt);
    }

    public String hash(String password, String salt) {
        String hash = HashHelper.hash(password, salt); // hacks! gebruik pepper in plaats van salt
        // pas eventueel een key stretch toe: x ronden uitvoeren
        return processRounds(hash, numberOfRounds(rounds));
    }

    private String processRounds(String hash, long r) {
        for (long i = 0; i < r; i++) {
            // niet zo efficient om dit met String te doen en HashHelper hash maakt ook steeds nieuwe objecten aan
            // wordt wel al heel snel erg traag
            hash = HashHelper.hash(hash);
        }
        return hash;
    }

    // Math.pow geeft een double terug, een long is gewenst
    // om testbaar te maken, naar eigen klasse toe zetten
    private long numberOfRounds(int load){
        int base = 10;
        long result = base; // base ^ 1

        for (int i = 0; i < load; i++) {
            result *= base;
        }
        return result;
    }
}
