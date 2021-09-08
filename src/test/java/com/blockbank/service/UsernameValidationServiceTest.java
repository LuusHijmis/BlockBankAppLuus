package com.blockbank.service;

/**
 * @author hannahvd
 */

import com.blockbank.database.repository.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsernameValidationServiceTest {

    private RootRepository rootRepository;
    private UsernameValidationService usernameService;
    private UserDao userDao;
    private AccountDao accountDao;
    private TransactionDao transactionDao;
    private AssetDao assetDao;

    @Autowired
    public UsernameValidationServiceTest(UserDao dao) {
        super();
        this.userDao = dao;
    }

    @BeforeAll
    void setup() {
        accountDao = Mockito.mock(AccountDao.class);
        rootRepository = new RootRepository(accountDao, userDao, transactionDao, assetDao);
        usernameService = new UsernameValidationService(rootRepository);
    }

    @AfterAll
    void tearDown() {
        rootRepository = null;
        usernameService = null;
    }

    @Test
    public void setUsernameServiceNotNull() {
        assertThat(usernameService).isNotNull();
    }

    @Test
    void testUniqueUsername() {
        assertFalse(usernameService.isValid("Harold"));
        assertTrue(usernameService.isValid("very_unique"));
    }

    @ParameterizedTest(name = "#{index} - Run test with username = {0}")
    @MethodSource("validUsernameProvider")
    void testValidUsernames(String username) {
        assertTrue(usernameService.isValid(username));
    }

    @ParameterizedTest(name = "#{index} - Run test with username = {0}")
    @MethodSource("invalidUsernameProvider")
    void testInvalidUsernames(String username) {
        assertFalse(usernameService.isValid(username));
    }

    static Stream<String> validUsernameProvider() {
        return Stream.of(
                "mkyong",
                "javaregex",
                "JAVAregex",
                "java.regex",
                "java-regex",
                "java_regex",
                "java.regex.123",
                "java-regex-123",
                "java_regex_123",
                "javaregex123",
                "123456",
                "java123",
                "01234567890123456789");
    }

    static Stream<String> invalidUsernameProvider() {
        return Stream.of(
                "abc",                      //invalid length 3; length must between 5-20
                "01234567890123456789a",    //invalid length 21; length must between 5-20
                "_javaregex_",              //invalid start and last character
                ".javaregex.",              //invalid start and last character
                "-javaregex-",              //invalid start and last character
                "javaregex#$%@123",         //invalid symbols, support dot, hyphen and underscore
                "java..regex",              //dot cant appear consecutively
                "java--regex",              //hyphen can't appear consecutively
                "java__regex",              //underscore can't appear consecutively
                "java._regex",              //dot and underscore can't appear consecutively
                "java.-regex",              //dot and hyphen can't appear consecutively
                " ",                        //empty
                "",                         //empty
                null);                      //empty
    }
}