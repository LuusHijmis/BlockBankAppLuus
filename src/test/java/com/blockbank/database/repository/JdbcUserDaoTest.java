package com.blockbank.database.repository;

import com.blockbank.database.domain.Address;
import com.blockbank.database.domain.ClientDetails;
import com.blockbank.database.domain.UserDetails;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class JdbcUserDaoTest {

    UserDao userDaoUnderTest;

    private final Logger logger = LoggerFactory.getLogger(JdbcUserDaoTest.class);

    @Autowired
    public JdbcUserDaoTest(UserDao dao) {
        super();
        this.userDaoUnderTest = dao;
        logger.info("New JdbcUserDaoTest");
    }

    @Test
    public void userDaoNotNull() {
        assertThat(userDaoUnderTest).isNotNull();
    }



    @Test
    void saveClient() {
        //arrange
        ClientDetails clientDetails = new ClientDetails("Pietje", "","Puk",
                LocalDate.parse("1988-04-02"),123456007,"pietjepuk@hva.nl");
        Address address = new Address("Oosthoutlaan",132,"","2215MC",
                "Voorhout","Nederland");
        UserDetails userDetails = new UserDetails("pietje","ejteip","1235",clientDetails,address);
        //act
        var actual = userDaoUnderTest.save(userDetails);
    }
    @Test
    void saveBank() {
        //arrange
        Address address = new Address("Oosthoutlaan",132,"","2215MC",
                "Voorhout","Nederland");
        UserDetails userDetails = new UserDetails("pietje","ejteip","1235",address);
        //act
        var actual = userDaoUnderTest.save(userDetails);
    }

    @Test
    void saveAdministrator() {
        //arrange
        UserDetails userDetails = new UserDetails("pietje","ejteip","1235");
        //act
        var actual = userDaoUnderTest.save(userDetails);
    }

}