package com.blockbank.database.repository;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.domain.Account;

public interface AccountDao {

    void save (Account account);
    void update(Account account);
    Account findByIban(String iban);
    Account findByClientId(int clientId);
}
