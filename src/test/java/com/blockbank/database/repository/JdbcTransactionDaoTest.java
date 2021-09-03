package com.blockbank.database.repository;

import com.blockbank.database.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcTransactionDaoTest {

    TransactionDao transactionDaoTest;

    private final Logger logger = LoggerFactory.getLogger(JdbcTransactionDaoTest.class);

    @Autowired
    public JdbcTransactionDaoTest(TransactionDao transactionDaoTest) {
        super();
        this.transactionDaoTest = transactionDaoTest;
        logger.info("New JdbcTransactionDaoTest");
    }
    @Test
    public void transactionDaoNotNullTest(){
        assertThat(transactionDaoTest).isNotNull();
    }

    @Test
    void save() {
        Asset bitcoin =  new Asset("1","Bitcoin", "BTC","BITCOIN",1.00);
        double exchangeRateBit = 48000.00;

        ClientDetails clientDetails = new ClientDetails("Harold", "","Stevens",
                LocalDate.parse("1973-09-25"),123456007,"info@hjstevens.nl");
        Address address = new Address("Pieter Woutersstraat",26,null,"2215MC",
                "Voorhout","");

        UserDetails userDetails = new UserDetails("Harold","dlorah","123",clientDetails,address);
        //

        Transaction transaction = new Transaction(userDetails,userDetails,bitcoin, LocalDateTime.now(),
                "Buy",1.00,exchangeRateBit,10.00);

        var actual = transactionDaoTest.save(transaction);
//        var found = transactionDaoTest.findByUserId(7);
//        Assertions.assertThat(found);
        System.out.println(actual);


    }

    @Test
    void delete() {
        fail();
    }

    @Test
    void findByUserId() {
//        var found = transactionDaoTest.findTransactionByUserId(2);
//        for(Transaction transaction2:found) {
//            System.out.println(transaction2);
//        }
    }
}