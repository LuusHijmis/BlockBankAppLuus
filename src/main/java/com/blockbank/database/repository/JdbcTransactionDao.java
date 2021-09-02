package com.blockbank.database.repository;

import com.blockbank.database.domain.Asset;
import com.blockbank.database.domain.Transaction;
import com.blockbank.database.domain.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTransactionDao implements TransactionDao {

    private final Logger logger = LoggerFactory.getLogger(JdbcTransactionDao.class);
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
                "insert into `transaction` (userID, transactionDateTime, transactionSort, amountAssets, exchangeRateTransaction, transactionFee, opposingUserID, assetID) values (?,?,?,?,?,?,?,?)",  Statement.RETURN_GENERATED_KEYS
        );
        ps.setInt(1, transaction.getUserDetails().getUserID());
        ps.setString(2, transaction.getLocalDateTime().toString());
        ps.setString(3, transaction.getTransactionDescription());
        ps.setDouble(4, transaction.getAssetAmount());
        ps.setDouble(5, transaction.getExchangeRate());
        ps.setDouble(6, transaction.getTransactionRate());
        ps.setInt(7, transaction.getUserDetails().getUserID());
        ps.setString(8, transaction.getAsset().getAssetID());
        return ps;
    }

    @Override
    public Transaction delete(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> findTransactionByUserId(int userID) {
        logger.debug("TransactionDao called for findTransactionByClientId");
        List<Transaction> transactions = jdbcTemplate.query(
                "select * from `transaction` where UserID = ?", new JdbcTransactionDao.TransactionRowMapper(), userID);
        if(transactions.size() >= 1) {
            return transactions;
        } else {
            return null;
        }
    }

    public int findUserForTransaction(int userID){
        int userForeignKey =
                jdbcTemplate.queryForObject(
                        "select userID from user where id = ?",
                        Integer.class,
                        userID);
        return userForeignKey;
    }

    private static class TransactionRowMapper implements RowMapper<Transaction> {
       RootRepository rootRepository;
        @Override
        public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
            Transaction tempResult= null;
            int transactionID = resultSet.getInt(1);
            int userID = resultSet.getInt(2);
            String dateTime = resultSet.getString(3);
            String transactionDescription = resultSet.getString(4);
            double amountAsset = resultSet.getDouble(5);
            double exchangeRate = resultSet.getDouble(6);
            double transactionFee = resultSet.getDouble(7);
            int opposingUserID = resultSet.getInt(8);
            int assetID = resultSet.getInt(9);
            UserDetails userDetails = rootRepository.findUserByUserId(userID);
            UserDetails oposingUserDetails = rootRepository.findUserByUserId(opposingUserID);

//            TODO findAssetByID moet nog gemaakt worden!
            Asset asset = new Asset(1,"Bitcoin","BIT","BLABLA",1.00);
            tempResult = new Transaction(userDetails, oposingUserDetails, asset, LocalDateTime.parse(dateTime), transactionDescription,
                    amountAsset, exchangeRate, transactionFee);
            tempResult.setTransactionID(transactionID);
            return tempResult;
        }

    }

}
