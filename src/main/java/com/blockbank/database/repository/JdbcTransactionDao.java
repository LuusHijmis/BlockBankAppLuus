package com.blockbank.database.repository;

import com.blockbank.database.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTransactionDao implements TransactionDao {

    private final Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);
    private JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        logger.info("New JdbcTransactionDao");
    }

    @Override
    public Transaction save(Transaction transaction) {
        logger.debug("transactionDao called for save");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> insertTransactionStatement(transaction, connection), keyHolder);
        int newKey = keyHolder.getKey().intValue();
        transaction.setTransactionID(newKey);
        return transaction;

    }

    private PreparedStatement insertTransactionStatement(Transaction transaction, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "insert into transaction (userID, transactionDateTime, transactionSort, amountAssets, exchangeRateTransaction, " +
                        "transactionFee, uposingUserID, AssetID values (?,?,?,?,?,?,?,?)"
        );
        ps.setInt(1, transaction.getUserDetails().getUserID());
        ps.setString(2, transaction.getLocalDateTime().toString());
        ps.setString(3, transaction.getTransactionDescription());
        ps.setDouble(4, transaction.getAssetAmount());
        ps.setDouble(5, transaction.getExchangeRate());
        ps.setDouble(6, transaction.getTransactionRate());
        ps.setInt(7, transaction.getUserDetails().getUserID());
        ps.setInt(8, transaction.getAsset().getAssetID());
        return ps;
    }

    @Override
    public Transaction delete(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> findByUserId(int transactionID) {
        return null;
    }
}
