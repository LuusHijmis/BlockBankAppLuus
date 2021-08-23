package com.blockbank.database.repository;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.domain.Account;

public interface AccountDao {

    Account save (Account account);
    Account update(Account account);
    Account findByIban(String iban);
    Account findByClientId(int clientId);
}
