package com.blockbank.database.repository;

/**
 * @author Alex Shijan
 */

import com.blockbank.database.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcAccountDao implements AccountDao{

    private final Logger logger = LoggerFactory.getLogger(JdbcAccountDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        logger.info("New JdcbAccountDao");
    }

    private PreparedStatement insertAccountStatement(Account account, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "insert into account (IBAN, balance, clientID) values (?,?,?)"
        );
        ps.setString(1, account.getIban());
        ps.setDouble(2, account.getBalance());
        ps.setInt(3, account.getClientId());
        return ps;
    }

    private PreparedStatement updateAccountStatement(Account account, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "update account SET balance = ? where IBAN = ?;"
        );
        ps.setDouble(1, account.getBalance());
        ps.setString(2, account.getIban());
        return ps;
    }

    @Override
    public void save(Account account) {
        logger.debug("accountDao called for save");
        jdbcTemplate.update(connection -> insertAccountStatement(account, connection));
    }

    @Override
    public void update(Account account) {
        logger.debug("accountDao called for update");
        jdbcTemplate.update(connection -> updateAccountStatement(account, connection));
    }

    @Override
    public Account findByIban(String iban) {
        logger.debug("accountDao called for findByIban");
        List<Account> accounts = jdbcTemplate.query(
                "select * from account where IBAN = ?", new AccountRowMapper(), iban);
        if(accounts.size() == 1) {
            return accounts.get(0);
        }
        return null;

    }

    @Override
    public Account findByClientId(int clientId) {
        logger.debug("accountDao called for findByClientId");
        List<Account> accounts = jdbcTemplate.query(
                "select * from account where clientID = ?", new AccountRowMapper(), clientId);
        if(accounts.size() == 1) {
            return accounts.get(0);
        }
        return null;
    }

    private static class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            String iban = resultSet.getString(1);
            double balance = resultSet.getDouble(2);
            int clientId = resultSet.getInt(3);
            Account account = new Account(clientId);
            account.setIban(iban);
            account.setBalance(balance);
            return account;
        }
    }

}
