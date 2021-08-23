package com.blockbank.database.repository;

import com.blockbank.database.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    private final Logger logger = LoggerFactory.getLogger(RootRepository.class);

    private AccountDao accountDao;

    @Autowired
    public RootRepository(AccountDao accountDao) {
        super();
        this.accountDao = accountDao;
        logger.info("New RootRepository");
    }

    public Account saveAccount(Account account) {
        return accountDao.save(account);
    }

    public Account updateAccount(Account account) {
        return accountDao.update(account);
    }

    public Account findByIban(String iban) {
        return accountDao.findByIban(iban);
    }

    public Account findByClientId(int clientId) {
        return accountDao.findByClientId(clientId);
    }


}
