package com.blockbank.service;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    TransactionService transactionServiceUnderTest;
    private final Logger logger = LoggerFactory.getLogger(TransactionServiceTest.class);

    @Autowired
    public TransactionServiceTest(TransactionService transactionServiceUnderTest) {
        this.transactionServiceUnderTest = transactionServiceUnderTest;
        logger.info("New TransactionServiceTest");
    }
    @Test
    public void TransactionServiceNotNullTest(){
        assertThat(transactionServiceUnderTest).isNotNull();
    }
}