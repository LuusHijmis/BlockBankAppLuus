package com.blockbank.database.repository;

/**
 * @author hannahvd, Alex Shijan
 * <description>
 */


import com.blockbank.database.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RootRepository {

    private final Logger logger = LoggerFactory.getLogger(RootRepository.class);

    private AccountDao accountDao;
    private UserDao userDao;
    private TransactionDao transactionDao;
    private AssetDao assetDao;

    @Autowired
    public RootRepository(AccountDao accountDao, UserDao userDao, TransactionDao transactionDao, AssetDao assetDao) {
        super();
        this.userDao = userDao;
        this.accountDao = accountDao;
        this.transactionDao= transactionDao;
        this.assetDao = assetDao;
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

    public UserDetails  findUserByUsername(String username) { return  userDao.findByUsername(username);}

    public Asset findAssetById(String assetId){ return assetDao.findAssetById(assetId);}

    public List<Asset> showAllAssets(){ return assetDao.showAllAssets();}

    public Asset updateAssets(Asset asset){ return assetDao.updateAsset(asset);}
//
//    public Transaction saveTransaction(Transaction transaction) {
//        return transactionDao.save(transaction);}

    public List<Transaction> findTransactionsByUSerID(int userID) {
        List<TransactionDTO> tempList = transactionDao.findTransactionByUserId(userID);
        List<Transaction> transactionList = null;
        if(tempList.isEmpty()) {
            for (TransactionDTO transactionDTO : tempList) {
                UserDetails userDetails = findUserByUserId(transactionDTO.getUserID());
                UserDetails oppossingUserDetails = findUserByUserId(transactionDTO.getOpposingUserID());
                Asset asset = findAssetById(String.valueOf(transactionDTO.getAssetID()));

                Transaction transaction = new Transaction(userDetails, oppossingUserDetails, asset, transactionDTO.getTransactionDateTime(),
                        transactionDTO.getTransactionSort(), transactionDTO.getAmountAssets(), transactionDTO.getExchangeRate(),
                        transactionDTO.getTransactionFee());
                transactionList.add(transaction);
            }
            return transactionList;
        } else {
            return null;
        }
    }


    public Transaction saveTransaction(TransactionDTO transactionDTO) {
//        //TODO implement after AssetDAO has findAssetByAssetID
        UserDetails userDetails = findUserByUserId(transactionDTO.getUserID());
        UserDetails opposingUserDetails = findUserByUserId(transactionDTO.getOpposingUserID());
        Asset asset = findAssetById(String.valueOf(transactionDTO.getAssetID()));
        LocalDateTime localDateTime = transactionDTO.getTransactionDateTime();
        Transaction transaction = new Transaction(userDetails,opposingUserDetails,asset,localDateTime,
                transactionDTO.getTransactionSort(),transactionDTO.getAmountAssets(),transactionDTO.getExchangeRate(),
                transactionDTO.getTransactionFee());
        return transactionDao.save(transaction);
    }
    public TransactionDTO findTransactionByID(int id) {
        return transactionDao.findTransactionByID(id);
    }

    //
    //TODO aanroep deleteTransaction fixen!
//    public Transaction deleteTransaction(Transaction transaction) {
//        return transactionDao.delete(Transaction transaction);
////    }
}
