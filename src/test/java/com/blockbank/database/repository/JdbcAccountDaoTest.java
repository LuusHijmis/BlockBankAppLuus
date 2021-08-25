package com.blockbank.database.repository;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.domain.Account;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JdbcAccountDaoTest {

    AccountDao accountDaoUnderTest;

    private final Logger logger = LoggerFactory.getLogger(JdbcAccountDaoTest.class);

    @Autowired
    public JdbcAccountDaoTest(AccountDao dao) {
        super();
        this.accountDaoUnderTest = dao;
        logger.info("New JdbcAccountDaoTest");
    }

    @Test
    public void accountDaoNotNull() {
        assertThat(accountDaoUnderTest).isNotNull();
    }

    @Test
    void saveAccountTest() {
        var instance = new Account(777);
        instance.setBalance(7777);
        instance.setIban("NL22RABO7777777777");
        var actual = accountDaoUnderTest.save(instance);
        var found = accountDaoUnderTest.findByUserId(777);
        assertThat(found).isEqualTo(actual);
    }

    @Test
    void updateAccountTest() {
        var original = accountDaoUnderTest.findByUserId(1);
        var update = new Account(1);
        update.setIban("NL22RABO9999999991");
        update.setBalance(999999);
        var actual = accountDaoUnderTest.update(update);
        var found = accountDaoUnderTest.findByUserId(1);
        assertThat(actual).isEqualTo(found);
        assertThat(found).isNotEqualTo(original);
    }

    @Test
    void findByIban() {
        var instance = accountDaoUnderTest.findByIban("NL22RABO9999999991");
        var expected = new Account(1);
        expected.setIban("NL22RABO9999999991");
        expected.setBalance(10000);
        assertThat(instance).isEqualTo(expected);
    }

    @Test
    void findByClientId() {
        var instance = accountDaoUnderTest.findByUserId(1);
        var expected = new Account(1);
        expected.setIban("NL22RABO9999999991");
        expected.setBalance(10000);
        assertThat(instance).isEqualTo(expected);
    }
}