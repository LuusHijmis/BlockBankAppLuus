package com.blockbank.database.repository;

/**
 * @author hannahvd, Alex Shijan
 * <description>
 */

import com.blockbank.database.domain.Account;
import com.blockbank.database.domain.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    private final Logger logger = LoggerFactory.getLogger(RootRepository.class);

    private AccountDao accountDao;
    private UserDao userDao;

    @Autowired
    public RootRepository(AccountDao accountDao, UserDao userDao) {
        super();
        this.userDao = userDao;
        this.accountDao = accountDao;
        logger.info("New RootRepository");
    }

    public Account saveAccount(Account account) {
        return accountDao.save(account);
    }

    public Account updateAccount(Account account) {
        return accountDao.update(account);
    }

    public Account findAccountByIban(String iban) {
        return accountDao.findByIban(iban);
    }

    public Account findAccountByUserId(int userId) {
        return accountDao.findByUserId(userId);
    }

    public UserDetails saveUserDetails(UserDetails userDetails) { return  userDao.save(userDetails);}

    public UserDetails findUserByUserId(int userId) { return  userDao.findUserById(userId);}
}
