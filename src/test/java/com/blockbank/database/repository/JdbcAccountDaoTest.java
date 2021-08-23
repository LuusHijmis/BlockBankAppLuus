package com.blockbank.database.repository;

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
    void findByIban() {
        var instance = accountDaoUnderTest.findByIban("NL22RABO9999999991");
        var expected = new Account(3);
        expected.setIban("NL22RABO9999999991");
        expected.setBalance(10000);
        assertThat(instance).isEqualTo(expected);
    }
}